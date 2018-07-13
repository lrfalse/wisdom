#!/bin/bash
##! @description: Config for Spring Boot's builtin launch script
##! @version: 1
##! @author: yanyunfeng
##! @date:   2018-07-07

filename=`basename "$0"`
prefix="${filename%.*}"

workspace=$(cd $(dirname $0) && pwd)/../

lib_dir=$workspace/lib
conf_dir=$workspace/conf
app_name=$prefix.jar

CONF_FOLDER=$conf_dir $lib_dir/$app_name $@
