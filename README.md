## TQ开放平台SDK使用文档
TQ sdk接入有两种方式：
##### 引入源码
 1. 将tq-open-api-sdk模块下源码导入你自己的项目。
 2. 使用姿势可见tq-open-api-sdk-test模块中的Main函数。

##### 引入jar包
  - 将release目录下的tq-open-api-sdk-*-RELEASE.jar添加到你项目的libs路径下。
  - 在你的项目依赖中添加以下依赖： 
  ```
  <dependency>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpclient</artifactId>
      <version>4.5.10</version>
  </dependency>
  <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.10.1</version>
  </dependency>
  <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.10.1</version>
  </dependency>
  <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.13.3</version>
  </dependency>
  <dependency>
      <groupId>com.google.guava</groupId>
      <artifactId>guava</artifactId>
      <version>29.0-jre</version>
  </dependency>
  ```
  - 使用姿势可见tq-open-api-sdk-test模块中的Main函数。