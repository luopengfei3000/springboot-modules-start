### SpringBoot对properties文件进行加密解密

#### 引入maven依赖
```
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>2.0.0</version>
</dependency>
```

#### 配置加解密Bean
```
@Configuration
public class EncryptionPropertyConfig {

	@Bean(name = "encryptablePropertyResolver")
	public EncryptablePropertyResolver encryptablePropertyResolver() {
		return new EncryptionPropertyResolver();
	}

	class EncryptionPropertyResolver implements EncryptablePropertyResolver {

		@Override
		public String resolvePropertyValue(String value) {
			if (StringUtils.isBlank(value)) {
				return value;
			}
			// 值以DES@开头的均为DES加密,需要解密
			if (value.startsWith("DES@")) {
				return resolveDESValue(value.substring(4));
			}
			// 不需要解密的值直接返回
			return value;
		}

		private String resolveDESValue(String value) {
			// 自定义DES密文解密
			return DESUtils.decrypt(value, DESUtils.KEY);
		}

	}
}
```

#### 编写加解密工具类
```
public class DESUtils {

    /** 默认key */
    public final static String KEY = "ScAKC0XhadTHT3Al0QIDAQAB";

    /**
     * DES加密
     *
     * @param data 待加密字符串
     * @param key 校验位
     * @return
     */
    public static String encrypt(String data, String key) {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }

    /**
     * DES解密
     *
     * @param cryptData 待解密密文
     * @param key 校验位
     * @return
     */
    public static String decrypt(String cryptData, String key) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
            // 把字符串解码为字节数组，并解密
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
        } catch (Exception e) {
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }

    public static void main(String[] args) {
        System.out.println("password::" + encrypt("cape", KEY));
    }

}
```

工具类中的main方法执行结果为：
```
CjaGvkg3YjM=
```

#### 修改properties/yml文件
```
url: jdbc:mysql://127.0.0.1:3306/springboottest
username: root
password: DES@CjaGvkg3YjM=
```