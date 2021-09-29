## Maven基础
当我们需要引入依赖包时，要把包放入classpath中。
其次我们需要规定项目的目录结构，每个目录中放什么东西。
此外，还需要配置环境，如jdk版本，编译打包的过程以及当前代码的版本号。
最后，我们需要使用命令行工具进行编译，才能够让项目在独立的服务器上编译、测试和部署，因此我们需要一个标准化的Java项目管理和构建工具。

Maven是专门为Java项目打造的管理和构建工具：
1. 标准化的项目结构
2. 标准化的构建流程
3. 具有依赖管理机制

#### Maven 项目管理
```
maven-project
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       ├── java
│       └── resources
└── target
```

pom.xml表明项目的元数据
```xml
<project>
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.itranswarp.learnjava</groupId>
	<artifactId>hello</artifactId>
	<version>1.0</version>
	<packaging>jar</packaging>
	<properties>
        ...
	</properties>
	<dependencies>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
	</dependencies>
</project>
```
groupId类似于Java的包名，artifactId类似于Java的类名，通常是项目名称，再加上version，以这三个标志作为唯一标识。

#### 依赖管理
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>1.4.2.RELEASE</version>
</dependency>
```
#### 依赖关系
compile：默认，编译时需要用到该jar包
test：编译Test时需要用到该jar包
runtime：编译时不需要，运行时需要
provided：编译时需要，运行时由JDK或某个服务器提供

除了第一次编译时因为下载需要时间会比较慢，后续过程因为有本地缓存，并不会重复下载相同的jar包。


在实际开发过程中，经常使用的命令有：

mvn clean：清理所有生成的class和jar；

mvn clean compile：先清理，再执行到compile；

mvn clean test：先清理，再执行到test，因为执行test前必须执行compile，所以这里不必指定compile；

mvn clean package：先清理，再执行到package。

大多数phase在执行过程中，因为我们通常没有在pom.xml中配置相关的设置，所以这些phase什么事情都不做。

经常用到的phase其实只有几个：

clean：清理
compile：编译
test：运行测试
package：打包

#### 模块管理
Maven可以有效管理多个模块，把每个模块当作一个独立的Maven项目，有各自独立的pom.xml。
我们可以提取出模块A和B的共同部分作为parent。

parent的packaging是pom而不是jar，因为parent本身不含任何Java代码。
A模块可以简化为
```xml
<parent>
        <groupId>com.itranswarp.learnjava</groupId>
        <artifactId>parent</artifactId>
        <version>1.0</version>
        <relativePath>../parent/pom.xml</relativePath>
</parent>
```

在编译时候需要在根目录创建一个pom.xml统一编译：
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>com.itranswarp.learnjava</groupId>
    <artifactId>build</artifactId>
    <version>1.0</version>
    <packaging>pom</packaging>
    <name>build</name>

    <modules>
        <module>parent</module>
        <module>module-a</module>
        <module>module-b</module>
        <module>module-c</module>
    </modules>
</project>
```
Maven根据根目录的pom.xml找到包括parent在内的4个<module>,一次性全部编译。