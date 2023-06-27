package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);


		JedisPubSub jedisPubSub = new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println("Received message: " + message);
			}
		};

		String channel = "myChannel";
		jedis.subscribe(jedisPubSub, channel);
		
		// Đóng kết nối Redis
		jedis.close();
	}
}
