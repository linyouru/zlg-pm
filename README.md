# zlg-pm 项目管理系统

## 部署
```
docker run -p 3306:3306 --restart always --name pm_mysql -e MYSQL_ROOT_PASSWORD=123456 -e TZ=Asia/Shanghai -v /data/pm/mysql/data:/var/lib/mysql -itd mysql:5.7.40
docker run -p 8618:8618 --restart always --name zlg_pm -v /home/zlgmcu/pm:/app -v /data/pm/logs:/logs/pm -itd openjdk:8 java -jar /app/zlg-pm-0.0.1-SNAPSHOT.jar
```
部署mysql后需要手动创建数据库,表则在项目启动过程中自动生成
将jar包放在容器挂载的目录下,更新代码只需更换jar包并重启容器

设置sql模式
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));



