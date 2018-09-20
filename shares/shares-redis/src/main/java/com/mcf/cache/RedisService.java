package com.mcf.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Pool;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

public class RedisService {
    @Autowired
    private Pool<Jedis> pool;

    public Pool<Jedis> getPool() {
        return pool;
    }

    public boolean exists(final String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.exists(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long delete(final String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.del(keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String rename(final String oldkey, final String newkey) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.rename(oldkey, newkey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long expire(final String key, final int seconds) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.expire(key, seconds);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long incrBy(final String key, final long integer) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.incrBy(key, integer);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long incr(final String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.incr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long decrBy(final String key, final long integer) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.decrBy(key, integer);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long decr(final String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.decr(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getJSONToObject(Class<?> clazz, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = jedis.get(key);
            return (T) JSON.toJavaObject((JSON) JSON.parse(value), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getString(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> mget(Class<?> clazz, final String... keys) {
        List<T> list = new ArrayList<T>();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            List<String> values = jedis.mget(keys);
            for (String value : values) {
                list.add((T) JSON.toJavaObject((JSON) JSON.parse(value), clazz));
            }
            return list;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> String setObjectToJSON(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String str = JSON.toJSONString(value);
            return jedis.set(key, str);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public <T> String setex(final String key, final int seconds, final T value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String str = JSON.toJSONString(value);
            return jedis.setex(key, seconds, str);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> String mset(final Map<String, T> map) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String[] keyValues = new String[map.size() * 2];
            int i = 0;
            for (String key : map.keySet()) {
                keyValues[2 * i] = key;
                keyValues[2 * i + 1] = JSON.toJSONString(map.get(key));
                i++;
            }
            return jedis.mset(keyValues);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> Long msetnx(final Map<String, T> map) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String[] keyValues = new String[map.size() * 2];
            int i = 0;
            for (String key : map.keySet()) {
                keyValues[2 * i] = key;
                keyValues[2 * i + 1] = JSON.toJSONString(map.get(key));
                i++;
            }
            return jedis.msetnx(keyValues);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public int hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hset(key, field, value).intValue();
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hget(key, field);
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hgetAll(key);
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    public long hdel(String hashKey, String taskId) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.hdel(hashKey, taskId);
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

}
