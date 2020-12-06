package com.boot.commons.core.redis;

import com.boot.commons.core.properties.SiteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisComponent {
    @Resource
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SiteProperties siteProperties;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(composeKey(key), TimeUnit.SECONDS);
    }

    public String composeKey(String key) {
        String pre = siteProperties.getAppName().concat("::");
        return key.startsWith(pre) ? key : pre.concat(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(composeKey(key));
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    public void del(String... keys) {
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                redisTemplate.delete(composeKey(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(composeKey(key));
    }

    //================================Object=================================

    /**
     * 获取符合pattern规则的所有key-value集合
     *
     * @param keyPattern 可支持正则表达式
     * @return 值
     */
    public Map<String, String> getValues(String keyPattern) {
        Set<String> keys = getKeys(keyPattern);
        Map<String, String> rtnMap = new HashMap<>(keys.size());
        for (String key : keys) {
            rtnMap.put(key, redisTemplate.opsForValue().get(key));
        }
        return rtnMap;
    }

    /**
     * 获取符合pattern规则的所有key
     *
     * @param keyPattern 可支持正则表达式
     * @return 值
     */
    public Set<String> getKeys(String keyPattern) {
        return redisTemplate.keys(composeKey(keyPattern));
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(String key, String value) {
        set(key, value, -1L);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public void set(String key, String value, long time) {
        redisTemplate.opsForValue().set(composeKey(key), value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public void expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(composeKey(key), time, TimeUnit.SECONDS);
        }
    }

    /**
     * 递增+1
     *
     * @param key 键
     * @return
     */
    public Long incr(String key) {
        return incr(key, 1L);
    }

    /**
     * 递增+n
     *
     * @param key 键
     * @param by  要增加几(大于0)
     * @return
     */
    public Long incr(String key, long by) {
        if (by < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(composeKey(key), by);
    }

    /**
     * 递减-1
     *
     * @param key 键
     * @return
     */
    public Long decr(String key) {
        return decr(key, 1L);
    }

    /**
     * 递减-n
     *
     * @param key 键
     * @param by  要减少几(大于0)
     * @return
     */
    public Long decr(String key, long by) {
        if (by < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(composeKey(key), -by);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(composeKey(key), item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hashMapGet(String key) {
        return redisTemplate.opsForHash().entries(composeKey(key));
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public void hashMapPut(String key, Map<String, String> map) {
        hashMapPut(key, map, -1);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public void hashMapPut(String key, Map<String, String> map, long time) {
        redisTemplate.opsForHash().putAll(composeKey(key), map);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public void hashPut(String key, String item, String value) {
        hashPut(key, item, value, -1);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public void hashPut(String key, String item, String value, long time) {
        redisTemplate.opsForHash().put(composeKey(key), item, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hashDel(String key, String... item) {
        redisTemplate.opsForHash().delete(composeKey(key), item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hashHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(composeKey(key), item);
    }

    /**
     * hash递增+1
     *
     * @param key  键
     * @param item 项
     * @return
     */
    public double hashIncr(String key, String item) {
        return hashIncr(key, item, 1);
    }

    /**
     * hash递增+n
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hashIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(composeKey(key), item, by);
    }

    /**
     * hash递减-1
     *
     * @param key  键
     * @param item 项
     * @return
     */
    public double hashDecr(String key, String item) {
        return hashDecr(key, item, 1);
    }

    /**
     * hash递减-n
     *
     * @param key  键
     * @param item 项
     * @param by   要减少几(大于0)
     * @return
     */
    public double hashDecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(composeKey(key), item, -by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<String> setGet(String key) {
        try {
            return redisTemplate.opsForSet().members(composeKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean setHasKey(String key, String value) {
        return redisTemplate.opsForSet().isMember(composeKey(key), value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long setPut(String key, String... values) {
        return redisTemplate.opsForSet().add(composeKey(key), values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long setPut(String key, long time, String... values) {
        Long count = redisTemplate.opsForSet().add(composeKey(key), values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public Long setSize(String key) {
        return redisTemplate.opsForSet().size(composeKey(key));
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setDel(String key, String... values) {
        return redisTemplate.opsForSet().remove(composeKey(key), values);
    }

    //===============================list=================================

    /**
     * 获取队列缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<String> listGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(composeKey(key), start, end);
    }

    /**
     * 获取队列缓存的长度
     *
     * @param key 键
     * @return
     */
    public Long listGetSize(String key) {
        return redisTemplate.opsForList().size(composeKey(key));
    }

    /**
     * 通过索引 获取队列中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public String listGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(composeKey(key), index);
    }

    /**
     * 将元素放入队列(右侧放入)
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void listRPush(String key, String value) {
        listRPush(key, value, -1);
    }

    /**
     * 将元素放入队列(右侧放入)
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void listRPush(String key, String value, long time) {
        redisTemplate.opsForList().rightPush(composeKey(key), value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 将list放入队列(右侧放入)
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void listRPush(String key, List<String> value) {
        listRPush(key, value, -1);
    }

    /**
     * 将list放入队列(右侧放入)
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void listRPush(String key, List<String> value, long time) {
        redisTemplate.opsForList().rightPushAll(composeKey(key), value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 将元素放入队列(左侧放入)
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void listLPush(String key, String value) {
        listLPush(key, value, -1);
    }

    /**
     * 将元素放入队列(左侧放入)
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void listLPush(String key, String value, long time) {
        redisTemplate.opsForList().leftPush(composeKey(key), value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 将list放入队列(左侧放入)
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public void listLPush(String key, List<String> value) {
        listLPush(key, value, -1);
    }

    /**
     * 将list放入队列(左侧放入)
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public void listLPush(String key, List<String> value, long time) {
        redisTemplate.opsForList().leftPushAll(composeKey(key), value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 从队列的左边出队一个元素
     *
     * @param key 键
     * @return
     */
    public String listLPop(String key) {
        return redisTemplate.opsForList().leftPop(composeKey(key));
    }

    /**
     * 从队列的右边出队一个元素
     *
     * @param key 键
     * @return
     */
    public String listRPop(String key) {
        return redisTemplate.opsForList().rightPop(composeKey(key));
    }

    /**
     * 从队列的右边出队一个元素，并将其追加到另一个列表
     *
     * @param key1 键1
     * @param key2 键2
     * @return
     */
    public String listRPopLPush(String key1, String key2) {
        return redisTemplate.opsForList().rightPopAndLeftPush(composeKey(key1), composeKey(key2));
    }

    /**
     * 根据索引修改队列中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public void listUpdIndex(String key, long index, String value) {
        redisTemplate.opsForList().set(composeKey(key), index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long listRemove(String key, long count, String value) {
        return redisTemplate.opsForList().remove(composeKey(key), count, value);
    }

    //===============================GEO=================================

    /**
     * 新增GEO坐标
     *
     * @param key    键
     * @param point  坐标点(x轴是经度longitude，y轴是纬度latitude)
     * @param member 地址标识
     * @return
     */
    public Boolean geoAdd(String key, Point point, String member) {
        Long addedNum = redisTemplate.opsForGeo().add(composeKey(key), point, member);
        return null != addedNum && addedNum > 0;
    }

    /**
     * 新增GEO坐标
     *
     * @param key      键
     * @param location 坐标地址
     * @return
     */
    public Boolean geoAdd(String key, RedisGeoCommands.GeoLocation<String> location) {
        Long addedNum = redisTemplate.opsForGeo().add(composeKey(key), location);
        return null != addedNum && addedNum > 0;
    }

    /**
     * 批量新增GEO坐标
     *
     * @param key       键
     * @param locations 坐标地址列表
     * @return
     */
    public Long geoAdd(String key, List<RedisGeoCommands.GeoLocation<String>> locations) {
        return redisTemplate.opsForGeo().add(composeKey(key), locations);
    }

    /**
     * 获取保存GEO坐标
     *
     * @param key     键
     * @param members 坐标标识
     * @return List<Point>  坐标点(x轴是经度longitude，y轴是纬度latitude)
     */
    public List<Point> gGet(String key, String... members) {
        return redisTemplate.opsForGeo().position(composeKey(key), members);
    }

    /**
     * 计算两个坐标的距离
     *
     * @param key     键
     * @param member1 坐标1标识
     * @param member2 坐标2标识
     * @param unit    单位 m 表示单位为米。km 表示单位为千米。mi 表示单位为英里。ft 表示单位为英尺。
     * @return
     */
    public Distance geoDist(String key, String member1, String member2, RedisGeoCommands.DistanceUnit unit) {
        return redisTemplate.opsForGeo().distance(composeKey(key), member1, member2, unit);
    }

    /**
     * 根据中心点坐标获取周边坐标
     *
     * @param key    键
     * @param center 中心点
     * @param radius 半径(单位:米)
     * @param limit  查询数量
     * @return
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> geoNearByXY(String key, Point center, double radius, int limit) {
        Circle circle = new Circle(center, new Distance(radius));
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(limit);
        return redisTemplate.opsForGeo().radius(composeKey(key), circle, args);
    }

    /**
     * 根据现有坐标为中心点获取周边坐标
     *
     * @param key    键
     * @param member 坐标标识
     * @param radius 半径(单位:米)
     * @param limit  查询数量
     * @return
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> geoNearByPlace(String key, String member, double radius, int limit) {
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(limit);
        return redisTemplate.opsForGeo().radius(composeKey(key), member, new Distance(radius), args);
    }

    /**
     * 获取geoHash值
     *
     * @param key     键
     * @param members 坐标标识
     * @return
     */
    public List<String> geoHash(String key, String... members) {
        return redisTemplate.opsForGeo().hash(composeKey(key), members);
    }

    /**
     * 删除
     *
     * @param key    键
     * @param member 坐标标识
     * @return
     */
    public Boolean geoRemove(String key, String member) {
        Long addedNum = redisTemplate.opsForGeo().remove(composeKey(key), member);
        return null != addedNum && addedNum > 0;
    }

    /**
     * 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key   键
     * @param start
     * @param end
     * @return
     */
    public Set<String> zRange(String key, int start, int end) {
        return redisTemplate.opsForZSet().range(composeKey(key), start, end);
    }

    /**
     * 从列表中移除匹配的值。
     *
     * @param key    键
     * @param values
     */
    public Long zRemove(String key, String... values) {
        return redisTemplate.opsForZSet().remove(composeKey(key), values);
    }

    /**
     * 用于将成员元素及其分数值加入到有序集当中。
     * 如果value已存在，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
     *
     * @param key   键
     * @param value
     * @param score
     * @return
     */
    public Boolean zAdd(String key, String value, double score) {
        return redisTemplate.opsForZSet().add(composeKey(key), value, score);
    }

}