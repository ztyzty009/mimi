##香蕉社区

##部署
###依赖
-Git
-JDK
-Maven
-Mysql
##步骤
-yum update
-yum install git  安装git
-mkdir App
-cd App
-git clone https://github.com/ztyzty009/mimi.git
-sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
-sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
-sudo yum install -y maven
-mvn -v
-mvn compile package
-cp src/main/resources/application.properties src/main/resources/application-production.properties
-vim src/main/resources/application-production.properties
-java -jar -spring.profiles.active=production $JAR_PATH/demo-0.0.1-SNAPSHOT.jar
-mvn compile flyway:migrate
-nohup java -jar -Dspring.profiles.active=production target/demo-0.0.1-SNAPSHOT.jar

###资料
[Markdown 插件](http://editor.md.ipandao.com/)


##脚本
```bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
逆向工程
```