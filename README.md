### 安装 & 配置教程

#### Java

1. 安装包

   jdk-8u301-linux-x64.rpm

2. 直接执行

   `rpm -ivh jdk-8u301-linux-x64.rpm`

#### MySQL

0. 查看并卸载干净原有的 mysql 和 mariadb

   ```shell
   # 检查 mariadb 依赖，忽略大小写查找 mariadb
   rpm -qa | grep -i mariadb
   # 卸载 mariadb
   rpm -ev --nodeps mariadb-{veriosn}
   # 检查 mysql 依赖，忽略大小写查找 mysql
   rpm -qa | grep -i mysql
   # 卸载 mariadb
   rpm -ev --nodeps mysql-{vserion}
   ```

1. 安装包

   mysql-8.0.26-1.el7.x86_64.rpm-bundle.tar

2. 解压安装包

   `tar -xvf mysql-8.0.26-1.el7.x86_64.rpm-bundle.tar `

3. 依次执行以下命令

   ```shell
   rpm -ivh mysql-community-common-8.0.26-1.el7.x86_64.rpm
   rpm -ivh mysql-community-libs-8.0.26-1.el7.x86_64.rpm 
   rpm -ivh mysql-community-client-plugins-8.0.26-1.el7.x86_64.rpm 
   rpm -ivh mysql-community-libs-8.0.26-1.el7.x86_64.rpm 
   rpm -ivh mysql-community-client-8.0.26-1.el7.x86_64.rpm 
   rpm -ivh mysql-community-server-8.0.26-1.el7.x86_64.rpm 
   ```

4. 启动 mysql 并查看运行状态

   ```shell
   systemctl start mysqld
   systemctl status mysqld
   ```

5. 查看初始化随机密码

   `cat /var/log/mysqld.log | grep password`

6. 登录 mysql

   `mysql -u root -p`

7. 重置密码

   `alter user 'root'@'localhost' identified by 'root.123456';`

   注：若想使用简单密码，先设置复杂密码，再先执行以下命令

   ```mysql
   -- 查看密码插件,获取
   show variables like 'validate_password%';
   -- 修改密码长度和策略验证规则
   set global validate_password.length=1;
   set global validate_password.policy=0;
   -- 重新修改密码
   alter user 'root'@'localhost' identified by '123456';
   ```

8. 开放远程登录权限

   ```mysql
   -- 使用 mysql 数据库
   use mysql;
   -- 查看用户和对应的访问地址
   select host,user from user;
   -- 将限制 root 用户的可访问地址
   update user set host='%' where user='root';
   -- 执行权限刷新
   flush privileges;
   ```

9. 开放 3306 端口

   ```shell
   # 查询 3306 端口是否开放
   firewall-cmd --query-port=3306/tcp
   # 开启 3306 端口
   firewall-cmd --zone=public --add-port=3306/tcp --permanent
   # 重新加载防火墙
   firewall-cmd --reload
   ```




#### ElasticSearch

