# 使用官方 OpenJDK 21 镜像
FROM openjdk:21

# 设置时区（环境变量即可）
ENV TZ=Asia/Shanghai

# 复制 JAR 文件到 /app 目录，使用通配符兼容版本号
COPY sky-server/target/sky-server-*.jar /app/sky-server.jar

# 声明暴露端口
EXPOSE 8080

# 启动命令（通过 sh -c 支持环境变量扩展）
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/sky-server.jar"]
