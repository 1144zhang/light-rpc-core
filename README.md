# light-rpc-core

> 一款基于 Netty + Kryo + Zookeeper 的轻量级高性能 RPC 框架，实现了服务注册发现、动态代理、负载均衡、异步调用等核心特性。

[![JDK](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![Netty](https://img.shields.io/badge/Netty-4.1.42-blue.svg)](https://netty.io/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](./LICENSE)

---

## 项目介绍

`light-rpc-core` 是一款从零实现的 RPC（远程过程调用）框架，旨在深入理解分布式系统中服务通信的底层原理。代码结构清晰，注释详细，非常适合学习和实践 RPC 核心机制。

### 核心特性

- **高性能网络通信**：基于 Netty NIO 实现，采用 Reactor 主从多线程模型
- **自定义通信协议**：魔数 + 序列化器编号 + 消息体长度 + 数据体的私有协议设计
- **多种序列化方式**：支持 Kryo、Protostuff、Hessian 等高效序列化框架
- **服务注册与发现**：基于 Zookeeper 实现，支持临时节点 + Watch 机制动态感知
- **客户端负载均衡**：支持随机、一致性哈希等多种负载均衡策略
- **Spring 注解集成**：通过 `@RpcService` / `@RpcReference` 注解简化服务注册与消费
- **心跳检测机制**：基于 Netty IdleStateHandler 保证长连接有效性
- **异步调用**：基于 CompletableFuture 实现请求响应异步解耦
- **SPI 扩展机制**：支持序列化方式可插拔扩展
- **服务分组与版本控制**：支持服务 group 和 version 管理
- **Gzip 压缩**：减少网络传输数据量

---

## 项目结构

```
light-rpc-core/
├── rpc-framework-common/     # 公共模块：枚举、工具类、异常、SPI 扩展加载器
├── rpc-framework-simple/     # RPC 核心实现：代理/注册/序列化/传输/负载均衡
├── hello-service-api/        # 示例服务接口定义
├── example-server/           # 服务提供者示例
├── example-client/           # 服务消费者示例
└── docs/                     # 相关文档
```

---

## 架构设计

```
┌─────────────────┐          ┌─────────────────┐
│  Service         │  注册    │   Zookeeper     │
│  Provider        │ ──────→ │   注册中心       │
│  (Netty Server)  │         │                 │
└─────────────────┘         └────────┬────────┘
                                      │ 服务发现
┌─────────────────┐                  │
│  Service         │ ←───────────────┘
│  Consumer        │
│  (Netty Client)  │
└─────────────────┘
```

**调用链路**：客户端动态代理 → 自定义协议编码 → Netty 网络传输 → 服务端解码 → 反射调用 → 返回结果

---

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.5+
- Zookeeper 3.5+

### 1. 启动 Zookeeper

```bash
docker pull zookeeper:3.5.8
docker run -d --name zookeeper -p 2181:2181 zookeeper:3.5.8
```

### 2. 克隆项目

```bash
git clone https://github.com/1144zhang/light-rpc-core.git
cd light-rpc-core
mvn clean install -DskipTests
```

### 3. 启动服务端

运行 `example-server` 模块中的 `NettyServerMain`

### 4. 启动客户端

运行 `example-client` 模块中的 `NettyClientMain`

---

## 已实现功能

- [x] 基于 Netty（NIO）的高性能网络传输
- [x] Kryo / Protostuff / Hessian 高效序列化
- [x] Zookeeper 服务注册与发现
- [x] Netty Channel 连接复用
- [x] CompletableFuture 异步调用与结果包装
- [x] Netty 心跳检测机制（IdleStateHandler）
- [x] 客户端负载均衡（随机 + 一致性哈希）
- [x] 服务分组（group）处理一接口多实现
- [x] Spring 注解集成（@RpcService / @RpcReference / @RpcScan）
- [x] 服务版本号管理
- [x] 自定义通信协议（魔数 + 序列化器编号 + 消息体长度 + 数据体）
- [x] Gzip 数据压缩
- [x] SPI 可插拔扩展机制

---

## 待优化方向

- [ ] 增加可配置化（序列化方式、注册中心实现方式），避免硬编码
- [ ] 服务容错机制（超时重试、熔断降级）
- [ ] 服务限流（令牌桶 / 漏桶算法）
- [ ] 编写单元测试，提高核心模块覆盖率
- [ ] 服务监控中心（类似 Dubbo Admin）
- [ ] 支持更多注册中心（Nacos / Etcd）
- [ ] 支持更多负载均衡算法（加权轮询 / 最少活跃调用）

---

## 技术栈

| 组件 | 技术 |
|------|------|
| 网络传输 | Netty 4.x |
| 序列化 | Kryo / Protostuff / Hessian |
| 注册中心 | Zookeeper + Curator |
| 动态代理 | JDK Proxy |
| 异步编程 | CompletableFuture |
| 框架集成 | Spring Framework |
| 压缩 | Gzip |
| 负载均衡 | 随机 / 一致性哈希 |

---

## License

[MIT License](./LICENSE)
