基于gradle编译的字符串加密demo
主项目依赖工程Encryt，对主工程内的字符串进行如下包装操作：`ByteCrypt.getString("test".getBytes())`
执行build后会自动检测主工程中的包装的字符串进行加密处理打入APK中，build结束会自动进行解密操作，保证工程内代码不变。