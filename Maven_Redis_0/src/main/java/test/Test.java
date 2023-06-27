package test;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String[] args) {
		// Kết nối tới Redis server (localhost)
		Jedis jedis = new Jedis("localhost");

		// Thực hiện các hoạt động Redis
		jedis.set("key", "value");
		String result = jedis.get("key");
		System.out.println(result);

		// Đóng kết nối Redis
		jedis.close();
	}
}
