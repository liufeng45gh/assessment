###指定java8环境镜像
FROM java:8

### 指定存储在容器内的目录
WORKDIR  /usr/local/assessment

### 复制 jar (assessment-0.0.1.jar) 到容器中并命名为 assessment.jar
ADD assessment-0.0.1.jar  assessment.jar

###声明启动端口号
EXPOSE 8080

###配置容器启动后执行的命令

ENTRYPOINT ["java","-jar","assessment.jar"]