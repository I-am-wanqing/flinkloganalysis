# 项目介绍
使用 Apache Flink 分布式流处理技术构建一个实时日志分析系统，该系统能够处理和分析大规模的日志数据，提供实时的监控和统计信息。

![image](https://github.com/user-attachments/assets/64f6bafc-fd31-4f1c-862d-a85b0c8409bd)

用户通过浏览器向Web Server发起Web请求，Web Server 处理请求并将产生的日志数据发送到 Kafka消息队列（项目使用简化的模拟脚本模拟服务器生产日志到Kafka消息队列），Kafka对日志数据进行传递并推送到Flink，Flink对接收到的日志数据进行实时处理，处理完成后将结果输出到MySQL数据库中，供后续查询和使用。

![image](https://github.com/user-attachments/assets/5373541b-eced-4fc7-a24b-483ce4af09b1)


# 运行流程
- 通过docker-compose up -d命令，运行docker-compose.yml文件，安装组件
- 在MySQL中执行 table.sql 文件，创建数据库表
- 在kafak中执行 docker exec -it kafka kafka-console-producer --broker-list localhost:9092 --topic access-logs ，创建名为access-logs的topic
- 将代码打包Jar包（Spring Boot项目打包Jar），上传到Flink执行
- 等待执行一会，使用Grafana连接到mysql，可视化分析结果

# 结果截图
![image](https://github.com/user-attachments/assets/115404b0-eb54-4e0f-bc2d-68d9d0817e7b)

![image](https://github.com/user-attachments/assets/ca24885f-e3e5-4ea1-b5bb-90ade048d4ac)
