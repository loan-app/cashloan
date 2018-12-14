#!/bin/bash

#Path to openssl binary
openssl=$(which openssl)
echo "$openssl"

#提示“请输入证书名称（英文或数字）：”
read -p "请输入证书名称（英文字母或数字）：" name

echo "证书名称为：$name"

#提示“请输入私钥密码”
read -p "请输入私钥密码（数字）：" password

echo "私钥密码为：$password"

echo "=====开始生成私钥======="

$openssl genrsa -out "$name"_pri.key 1024

echo "=====创建证书请求====="

$openssl req -new -key "$name"_pri.key -out "$name"_pri.csr -days 3650 -subj "/CN=RootCA/OU=XinYan/O=XinYan/L=Shanghai/ST=Shanghai/C=CN"


echo "=====创建数字证书====="
$openssl req -new -x509 -key "$name"_pri.key -out "$name"_pub.cer -days 3650 -subj "/CN=RootCA/OU=XinYan/O=XinYan/L=Shanghai/ST=Shanghai/C=CN"

echo "======将私钥和证书合并为PFX文件"

$openssl pkcs12 -export -in "$name"_pub.cer -inkey "$name"_pri.key -out "$name"_pri.pfx -passout pass:$password -name "$name_alias"


echo "清除中间文件"

rm "$name"_pri.key
rm "$name"_pri.csr





