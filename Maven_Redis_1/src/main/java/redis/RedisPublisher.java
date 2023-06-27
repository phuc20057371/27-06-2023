package redis;

import redis.clients.jedis.Jedis;

public class RedisPublisher {
	public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        String channel = "myChannel";
        String message = "Hello Redis!";
        jedis.publish(channel, message);

        jedis.close();
    }
}
