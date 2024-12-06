# 使用基础镜像
FROM eclipse-temurin:17-jdk

# 设置工作目录
WORKDIR /app

# 复制生成的 JAR 文件到容器中
COPY app-manage-server/target/app-manage-server-1.0-SNAPSHOT.jar app.jar

# 启动应用
CMD ["java", "-jar", "app.jar"]

# 暴露端口
EXPOSE 8089
