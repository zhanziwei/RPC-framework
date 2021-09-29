## 线程池实现原理
线程池是我们常用的Java并发编程的工具。
### 线程池是什么
是JUC的ThreadPoolExecutor类，由于线程过多，会带来额外的开销，包括销毁和调度，也降低了计算性能。使用线程池来维护多个线程，等待管理者为线程分配任务。

使用线程池的好处：

* 降低资源消耗：重复利用线程
* 提高响应速度：任务到达时不需要创建线程
* 提高线程的可管理性：使用线程池进行统一的分配、调优和监控
* 具有更多的功能。如ScheduledThreadPoolExecutor，允许任务延期执行或定期执行。

![图1 ThreadPoolExecutor UML类图](https://p1.meituan.net/travelcube/912883e51327e0c7a9d753d11896326511272.png)

Executor接收存有任务的运行逻辑的Runnable对象，由Executor框架完成线程的调配和任务的执行部分。ExecutorService接口增加了能力：补充可以为一个或一批异步任务生成Future的方法；提供了管控线程池的方法。ThreadPoolExecutor一方面维护自身的生命周期，一方面管理线程和任务。

### 生命周期管理

线程池内部使用一个变量维护两个值：运行状态runState和线程数量workerCount。ctl的高3位保存runState，低29位保存workerCount。用一个变量去存储，不会出现不一致的情况，不必为了维护两者的一致而占用锁资源。经常要同时判断线程池运行状态和线程数量的情况。

* RUNNING：接受新提交的任务，也能处理阻塞队列的任务
* SHUTDOWN：不再接受新提交的任务
* STOP：不再处理任务，并中断进行中的任务
* TIDYING：所有任务终止，workerCount为0
* TERMINATED：在terminated方法执行完后进入该状态。