springboot整合rabbitmq

业务需求：
通过支付宝向余额宝转账，采用异步处理，使用rabbitmq实现

该实现中存在两个问题：
1、消息丢失问题（支付宝发送消息成功，余额宝消费消息，操作余额宝DB出现异常）
解决方法：增加message表，字段state状态（confirm/unconfirm）,Timer定时器将为余额宝未消费成功的消息定时发送消息给rabbitmq

2、重复消费（修改message表中state状态发生异常）
解决方法：在余额宝message表通过messageId查询结果之后再根据结果，操作更新金额（操作message表和account表在同一个事务中）


支付宝工程：
1、主要实现发送消息
2、定时器发送未confirm的消息


