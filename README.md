# cashloan
在服务器上配置本机的ssh key，方法如下：
可以搜下获取ssh公钥
本地 cat ~/.ssh/id_rsa.pub 复制里面的内容
服务端 vi ~/.ssh/authorized_keys，黏贴到这里

发布api目录是：
cd deploy目录下面，执行
sh publishApiToTest233.sh

发布manage目录是：
cd deploy目录下面，执行
sh publishManageToTest233.sh
