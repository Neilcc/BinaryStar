# BinaryStar

# a sub-thread struct for Android display

## Based on clean architecture

All the logical process are posted to a subthread to exectue, for better display performance.

Logical module are organized as command struct.

Refer to the demo app for more informations

### Feel free to contact me 
zccneil@163.com


# 双子星

## 基于clean architechture 的Android ui 线程分离方案与架构

### 所有的逻辑行为将会在子线程中执行

### 逻辑行为需要集成Command 对象进行封装，以保证逻辑复用。


# How to Use

1. ADD Maven :

```
 maven{
         url "https://dl.bintray.com/zccneil/BinaryStar/"
     }
```

2. Compile gradle:

```
        compile 'com.zcc.binarystar:core:1.0.0'
```