# 章节一
## 什么是Servlet 
Servlet是基于JavaTM技术的Web组件，由容器管理，可生成动态内容。与其他基于Java技术的组件一样，servlet是独立于平台的Java类，这些类被编译为与平台无关的字节码，可以将其动态加载到支持Java技术的Web服务器中并由其运行。容器有时称为Servlet引擎，是提供Servlet功能的Web服务器扩展。Servlet通过Servlet容器实现的请求/响应范例与Web客户端进行交互。

## 什么是Servlet 容器

Servlet容器是Web服务器或应用程序服务器的一部分，该Web服务器或应用程序服务器提供通过其发送请求和响应的网络服务，解码基于MIME的请求并格式化基于MIME的响应。servlet容器还包含和管理servlet的整个生命周期。Servlet容器可以内置到主机Web服务器中，也可以通过该服务器的本机扩展API作为Web服务器的附加组件安装。Servlet容器也可以内置到或可能安装到启用Web的应用程序服务器中。所有Servlet容器都必须支持HTTP作为请求和响应的协议，但是可能支持其他基于请求/响应的协议，例如HTTPS（基于SSL的HTTP）。容器必须实现的HTTP规范的必需版本是HTTP / 1.1和HTTP / 2。当支持HTTP / 2时，Servlet容器必须支持“ h2”和“ h2c”协议标识符（如HTTP / 2 RFC 1-1节3.1中所指定）。这意味着所有servlet容器都必须支持ALPN。因为容器可能具有RFC 7234（HTTP / 1.1缓存）中描述的缓存机制，所以它可以在将客户端的请求传递给servlet之前修改来自客户端的请求，可以在将其发送给客户端之前修改servlet产生的响应，或者可以响应请求，而无需按照RFC 7234的要求将其传递给Servlet。Servlet容器可能会对Servlet执行的环境施加安全限制。在Java平台标准版（J2SE，v.1.3或更高版本）或Java平台企业版（Java EE，v.1.3或更高版本）环境中，应使用Java平台定义的权限体系结构来放置这些限制。例如，某些应用程序服务器可能会限制Thread对象的创建，以确保不会对容器的其他组件产生负面影响。Java SE 8是必须用来构建servlet容器的基础Java平台的最低版本。

## 示例

以下是典型访问的事件：

1. 客户端（如 web 浏览器）要访问 Web 服务器，并发送一个 HTTP 请求；
2. Web 服务器接收到请求并且交给 servlet 容器处理，servlet 容器可以运行在与宿主 Web 服务器同一个进程中，也可以是同一主机的不同进程，或者位于不同的主机的 Web 服务器中，对请求进行处理。
3. servlet 容器根据 servlet 配置选择相应的 servlet，并调用代表请求和响应的对象。
4. servlet 通过请求对象得到远程用户，HTTP POST 参数和其他有关数据可能作为请求的一部分随请求一起发送过来。Servlet 执行我们编写的任意的逻辑，然后动态产生响应内容发送回客户端。发送数据到客户端是通过响应对象完成的。
5. 一旦 servlet 完成请求的处理，servlet 容器必须确保响应正确的输出，并且将控制权还给宿主 Web 服务器。

## Servlet与其他技术的比较


从功能上看，servlet 位于Common Gateway Interface（公共网关接口，简称 CGI）程序和私有的服务器扩展如 Netscape Server API（NSAPI）或 Apache Modules 这两者之间。
相对于其他服务器扩展机制 Servlet 有如下优势：
- 它们通常比 CGI 脚本更快，因为采用不同的处理模型。
- 它们采用标准的 API 从而支持更多的Web 服务器。
- 它们拥有 Java 编程语言的所有优势，包括容易开发和平台无关。
- 它们可以访问 Java 平台提供的大量的 API。

## 与 Java EE 的关系

Java Servlet API v.4.0 是Java平台企业版8的必需API。部署在其中的Servlet容器和Servlet必须满足Java EE规范中描述的在Java EE环境中执行的其他要求

## 与Java Servlet规范版本2.5的兼容性

### 处理注解
在 Servlet 2.5 中, metadata-complete 只影响在部署时的注释扫描。 web-fragments 的概念在 servlet 2.5 并不存在。然而在 servlet 3.0 和之后,metadata-complete 影响扫描所有的在部署时指定部署信息和 web-fragments 注释。注释的版本的描述符必须不影响你扫描在一个web应用程序。除非 metadata-complete 指定，规范的一个特定版本的实现必须扫描所有配置的支持的注解。

# Servlet接口

Servlet接口是Java Servlet API的核心抽象。所有servlet都可以通过扩展实现该接口的类来直接或更普遍地实现此接口。Java Servlet API中实现Servlet接口的两个类是GenericServlet和HttpServlet。对于大多数目的，开发人员将扩展HttpServlet来实现其servlet。

## 请求处理方法

基础的Servlet接口定义了用于处理客户端请求的service()方法。对于servlet容器路由到servlet实例的每个请求，都会调用此方法。处理对Web应用程序的并发请求通常需要Web 开发人员去设计适合多线程执行的Servlet，从而保证 service 方法能在一个特定时间点处理多线程并发执行。（。通常，Web容器通过在不同线程上并发执行服务方法来处理对同一servlet的并发请求。
译者注： Servlet 默认是线程不安全的，需要开发人员处理多线程问题）
通常 Web 容器对于并发请求将使用同一个 servlet 处理，并且在不同的线程中并发执行 service 方法。

### 基于 HTTP 规范的请求处理方法

HttpServlet 抽象子类在基本的 Servlet 之上添加了些协议相关的方法，并且这些方法能根据 HTTP 请求类型自动的由 HttpServlet 中实现的 service 方法转发到相应的协议相关的处理方法上。这些方法是：
- doGet 处理 HTTP GET 请求
- doPost 处理 HTTP POST 请求
- doPut 处理 HTTP PUT 请求
- doDelete 处理 HTTP DELETE 请求
- doHead 处理 HTTP HEAD 请求
- doOptions 处理 HTTP OPTIONS 请求
- doTrace 处理 HTTP TRACE 请求

一般的，开发基于 HTTP 的 servlet 时, Servlet 开发人员只需去实现 doGet 和 doPost 请求处理方法即可。如果开发人员想使用其他处理方法，其使用方式跟之前的是类似的，即 HTTP 编程都是类似。

### 附加方法
doPut 和 doDelete 方法允许 Servlet 开发人员让支持 HTTP/1.1 的客户端使用这些功能。HttpServlet 中的 doHead 方法可以认为是 doGet 方法的一个特殊形式，它仅返回由 doGet 方法产生的 header 信息。doOptions 方法返回当前 servlet 支持的 HTTP 方法。doTrace 方法返回的响应包含TRACE请求的所有头信息。

### 有条件GET支持

HttpServlet 定义了用于支持有条件 GET 操作的 <code>javax.servlet.http.HttpServlet#getLastModified</code> 方法。所谓的有条件 GET 操作是指客户端通过 GET 请求获取资源时，当资源自第一次获取那个时间点发生更改后才再次发生数据，否则将使用客户端缓存的数据。在一些适当的场合，实现此方法可以更有效的利用网络资源，减少不必要的数据发


## 实例数量

通过注解描述的[（第8章 注解和可插拔性）](#Annotations)或者在 Web 应用程序的部署描述符[（第14章 部署描述符）](#Deployment)中描述的 servlet 声明，控制着 servlet 容器如何提供 servlet 实例。
对于未托管在分布式环境中（默认）的 servlet 而言，servlet 容器对于每一个 Servlet 声明必须且只能产生一个实例。不过，如果 Servlet实现了 SingleThreadModel 接口，servlet 容器可以选择实例化多个实例以便处理高负荷请求或者串行化请求到一个特定实例。
如果 servlet 以分布式方式进行部署，容器可以为每个 Java Virtual Machine (JVM™) 的每个 Servlet 声明产生一个实例。然而，如果在分布式环境中 servlet 实现了 SingleThreadModel 接口，此时容器可以为每个容器的 JVM 实例化多个 Servlet 实例。

### 关于 Single Thread Model
使用SingleThreadModel接口可确保在给定servlet实例的service方法中一次仅执行一个线程。重要的是要注意，此保证仅适用于每个servlet实例，因为容器可以选择合并这些对象。一次可被一个以上servlet实例访问的对象（例如HttpSession实例）可能在任何特定时间对多个servlet可用，包括那些实现SingleThreadModel的servlet。建议开发人员采取其他方法解决这些问题，而不要实现此接口，例如，避免使用实例变量或同步访问这些资源的代码块。此版本的规范中不推荐使用SingleThreadModel接口。
(译者注: <code>javax.servlet.SingleThreadModel</code> 请注意，SingleThreadModel不能解决所有线程安全问题。例如，即使使用SingleThreadModel servlet，会话属性和static 变量仍然可以同时由多个线程上的多个请求访问。建议开发人员采取其他方法来解决这些问题，而不是实现此接口，例如避免使用实例变量或同步访问这些资源的代码块。 Servlet API版本2.4中废弃，无替代品)
## Servlet 生命周期

Servlet是通过定义良好的生命周期进行管理的，该生命周期定义了如何加载和实例化，初始化，初始化，处理来自客户端的请求以及将其退出服务。API中的生命周期由javax.servlet.Servlet接口的init，service和destroy方法表示，所有servlet必须直接或通过GenericServlet或HttpServlet抽象类间接实现。

### 加载并实例化
Servlet 容器负责加载和实例化 Servlet。加载和实例化可以发生在容器启动时，或者延迟初始化直到容器决定有请求需要处理时。
当 Servlet 引擎启动后，servlet 容器必须定位所需要的 Servlet 类。Servlet 容器使用普通的 Java 类加载设施加载 Servlet 类。可以从本地文件系统或远程文件系统或者其他网络服务加载。
加载完 Servlet 类后，容器就可以实例化它并使用了。
(译者注 ： 在web.xml的该Servlet的配置中加入<code>&lt;load-on-startup&gt;&lt;/load-on-startup&gt;</code>属性,或者注解<code>javax.servlet.annotation.WebServlet#loadOnStartup</code>，属性值为0或者整数表示延迟加载，整数的大小表示优先级，数值越小表示优先级越高，如果是负数的话就是默认值也就是延迟加载了。)

### 初始化
servlet 对象实例化后，容器必须初始化 servlet 之后才能处理客户端的请求。初始化的目的是使 Servlet 能读取持久化配置数据，初始化一些代价高的资源（比如JDBC™ API 连接），或者执行一些一次性的动作。容器通过调用 Servlet 实例的 init 方法完成初始化，init 方法定义在Servlet 接口中，并且提供一个唯一的 ServletConfig 接口实现的对象作为参数，该对象每个 Servlet 实例一个。配置对象允许 Servlet 访问由 Web 应用配置信息提供的键-值对的初始化参数。该配置对象也提供给Servlet 去访问一个 ServletContext 对象，ServletContext 描述了Servlet 的运行时环境。请参考[第4章 “Servlet 上下文”](#context)获取ServletContext 接口的更多信息。

### 初始化出错处理

在初始化期间，Servlet实例可以引发UnavailableException或ServletException。在这种情况下，不得将Servlet置于活动服务中，而必须由Servlet容器释放它。不会调用destroy方法，因为它被认为是初始化失败。
初始化失败后，容器可以实例化并初始化新实例。该规则的例外是，当UnavailableException指示最短的不可用性时间，并且容器必须等待该时间段过去之后，才能创建和初始化新的Servlet实例。

### 工具注意事项

工具加载和自检Web应用程序时，静态初始化方法的触发与init方法的调用是有区别的。在调用Servlet接口的init方法之前，开发人员不应假定Servlet在活动容器运行时中。例如，当仅调用静态（类）初始化方法时，servlet不应尝试建立与数据库或Enterprise JavaBeansTM容器的连接。
（译者注:  Servlet 生命周期必须经历init() 方法，不应该在Servlet 初始化前不应尝试建立与数据库或Enterprise JavaBeansTM容器的连接）

### 访问请求处理

servlet 完成初始化后，servlet 容器就可以使用它处理客户端请求了。客户端请求由 ServletRequest 类型的请求对象表示。servlet 封装响应并返回给请求的客户端，该响应由 ServletResponse 类型的响应对象表示。这两个对象是由容器通过参数传递到Servlet接口的service方法的。
在 HTTP 请求的场景下，容器提供的请求和响应对象具体类型分别是HttpServletRequest 和 HttpServletResponse。 

需要注意的是，由 servlet 容器初始化的某个 servlet 实例在服务期间，可以在其生命周期中不处理任何请求。

#### 多线程问题

Servlet容器可以通过Servlet的服务方法发送并发请求。为了处理请求，Servlet开发人员必须为服务方法中的多线程并发处理做好充分准备。尽管不建议这样做，但开发人员的另一种方法是实现SingleThreadModel接口，该接口要求容器保证服务方法中一次仅存在一个请求线程。servlet容器可以通过序列化servlet上的请求或维护servlet实例池来满足此要求。如果Servlet是已标记为可分发的Web应用程序的一部分，则容器可以在应用程序所分布的每个JVM中维护一个Servlet实例池。对于未实现SingleThreadModel接口的Servlet，如果已使用synchronized 关键字定义了服务方法（或HttpServlet抽象类的服务方法派发到的方法，例如doGet或doPost），则Servlet容器不能使用实例池方法，但必须通过它序列化请求。强烈建议开发人员在这种情况下不要同步Service 方法（或分配给它的方法），因为这会对性能产生不利影响。

### 请求处理时的异常

servlet 在处理一个请求时可能抛出 ServletException 或UnavailableException 异常。ServletException 表示在处理请求时出现了一些错误，容器应该采取适当的措施清理掉这个请求。
UnavailableException 表示 servlet 目前无法处理请求，或者临时性的或者永久性的。
</b>
如果 UnavailableException 表示的是一个永久性的不可用，servlet 容器必须从服务中移除这个 servlet，调用它的 destroy 方法，并释放servlet 实例。所有被容器拒绝的请求，都会返回一个 SC_NOT_FOUND (404) 响应。
</b>
如果 UnavailableException 表示的是一个临时性的不可用，容器可以选择在临时不可用的这段时间内路由任何请求到 Servlet。所以在这段时间内被容器拒绝的请求，都会返回一个 SC_SERVICE_UNAVAILABLE (503) 响应状态码，且同时会返回一个 Retry-After 头指示此 servlet 什么时候可用。容器可以选择忽略永久性和临时性不可用的区别，并把UnavailableException 视为永久性的，从而 servlet 抛出UnavailableException 后需要把它从服务中移除。

#### 异步处理

有时候，Filter及/或 Servlet 在生成响应之前必须等待一些资源或事件以便完成请求处理。比如，Servlet 在进行生成一个响应之前可能等待一个可用的 JDBC 连接，或者一个远程 web 服务的响应，或者一个 JMS 消息，或者一个应用程序事件。在 Servlet 中等待是一个低效的操作，因为这是阻塞操作，从而白白占用一个线程或其他一些受限资源。许多线程为了等待一个缓慢的资源比如数据库经常发生阻塞，可能引起线程饥饿，且降低整个 Web 容器的服务质量。
</B>
引入了异步处理请求的能力，使线程可以返回到容器，从而执行更多的任务。当开始异步处理请求时，另一个线程或回调可以或者产生响应，或者调用完成（complete）或请求分派（dispatch），这样，它可以在容器上下文使用 AsyncContext.dispatch 方法运行。一个典型的异步处理事件顺序是：

1. 请求被接收到，通过一系列如用于验证的等标准的 filter 之后被传递到 Servlet。
2. servlet 处理请求参数及（或）内容体从而确定请求的类型。
3. 该 servlet 发出请求去获取一些资源或数据，例如，发送一个远程web 服务请求或加入一个等待 JDBC 连接的队列。
4. servlet 不产生响应并返回。
5. 过了一段时间后，所请求的资源变为可用，此时处理线程继续处理事件，要么在同一个线程，要么通过 AsyncContext 分派到容器中的一个资源上。

Java 企业版的功能，如第15.2.2节，在“Web应用环境”和第15.3.1节，在“EJB调用的安全标识传播”，仅在初始化请求的线程执行，或者请求经过AsyncContext.dispatch 方法被分派到容器。Java 企业版的功能可能支持由 AsyncContext.start(Runnable) 方法使用其他线程直接操作响应对象。

第八章描述的 @WebServlet 和 @WebFilter 注解有一个属性——asyncSupported，boolean 类型默认值为 false。当 asyncSupported 设置为 true，应用通过执行 startAsync（见下文）可以启动一个单独的线程中进行异步处理，并把请求和响应的引用传递给这个线程，然后退出原始线程所在的容器。这意味着响应将遍历（相反的顺序）与进入时相同的过滤器（或过滤器链）。直到 AsyncContext 调用 complete（见下文）时响应才会被提交。如果异步任务在容器启动的分派之前执行，且调用了 startAsync 并返回给容器，此时应用需负责处理请求和响应对象的并发访问。


从一个 Servlet 分派时，把 asyncSupported=true 设置为 false 是允许的。这种情况下，当 servlet 的 service 方法不支持异步退出时，响应将被提交，且容器负责调用 AsyncContext 的 complete，以便所有感兴趣的 AsyncListener 得到触发。过滤器作为清理要完成的异步任务持有的资源的一种机制，也应该使用 AsyncListener.onComplete 触发的结果。

从一个同步 Servlet 分派到另一个异步 Servlet 是非法的。不过与该点不同的是当应用调用 startAsync 时将抛出 IllegalStateException。这将允许 servlet 只能作为同步的或异步的 Servlet。

应用在一个与初始请求所用的不同的线程中等待异步任务直到可以直接写响应，这个线程不知道任何过滤器。如果过滤器想处理新线程中的响应，那就必须在处理进入时的初始请求时包装响应，并且把包装的响应传递给链中的下一个过滤器，并最终交给 Servlet。因此，如果响应是包装的（可能被包装多次，每一个过滤器一次），并且应用处理请求并直接写响应，这将只写响应的包装对象，即任何输出的响应都会由响应的包装对象处理。当应用在一个单独的线程中读请求时，写内容到响应的包装对象，这其实是从请求的包装对象读取，并写到响应的包装对象，因此对包装对象操作的所有输入及（或）输出将继续存在。


如果应用选择这样做的话，它将可以使用 AsyncContext 从一个新线程发起到容器资源的分派请求。这将允许在容器范围内使用像 JSP 这种内容生成技术。

除了注解属性外，我们还添加了如下方法/类：

- ServletRequest
    - public AsyncContext startAsync(ServletRequest req, ServletResponse res)。</b> 这个方法的作用是将请求转换为异步模式，并使用给定的请求及响应对象和 getAsyncTimeout 返回的超时时间初始化它的 AsyncContext。ServletRequest 和 ServletResponse 参数必须是与传递给 servlet 的 service 或 filter 的 doFilter方法相同的对象，或者是 ServletRequestWrapper 和ServletResponseWrapper 子类的包装对象。当应用退出 service 方法时，调用该方法必须确保响应没有被提交。当调用返回的AsyncContext 的 AsyncContext.complete 或 AsyncContext 超时并且没有监听器处理超时时，它将被提交。异步超时定时器直到请求和它关联的响应从容器返回时才启动。AsyncContext 可以被异步线程用来写响应，它也能用来通知没有关闭和提交的响应。
    
如果请求在不支持异步操作的servlet或filter范围中调用 startAsync，或者响应已经被提交或关闭，或者在同一个分派期间重复调用，这些是非法的。从调用 startAsync 返回的 AsyncContext 可以接着被用来进行进一步的异步处理。调用返回的 AsyncContext 的hasOriginalRequestResponse() 方法将返回 false，除非传过去的ServletRequest 和 ServletResponse 参数是最原始的那个或不是应用提供的包装器。
<br>
在请求设置为异步模式后，在入站调用期间添加的一些请求及（或）响应的包装器可能需要在异步操作期间一直保持，并且它们关联的资源可能也不会释放，出站方向调用的所有过滤器可以以此作为一个标志。 一个在入站调用期间的过滤器应用的 ServletRequestWrapper 可以被出站调用的过滤器释放，只有当给定的 ServletRequest 是由 AsyncContext 初始化的且通过调用 AsyncContext.getRequest() 返回的，不包括之前说的 ServletRequestWrapper。这规则同样适用于ServletResponseWrapper 实例。

- public AsyncContext startAsync() 是一个简便方法，使用原始的请求和响应对象用于异步处理。请注意，如果它们在你想调用此方法之前被包装了，这个方法的使用者应该刷出（flush）响应，确保数据写到被包装的响应中没有丢失。
- public AsyncContext getAsyncContext() – 返回由 startAsync 调用创建的或初始化的 AsyncContext。如果请求已经被设置为异步模式，调用 getAsyncContext 是非法的。
- public boolean isAsyncSupported() – 如果请求支持异常处理则返回 true，否则返回 false。一旦请求传给了过滤器或 servlet 不支持异步处理（通过指定的注解或声明），异步支持将被禁用。
- public boolean isAsyncStarted() – 如果请求的异步处理已经开始将返回 true，否则返回 false。如果这个请求自从被设置为异步模式后已经使用任意一个 AsyncContext.dispatch 方法分派，或者成功调用了 AsynContext.complete 方法，这个方法将返回false。
- public DispatcherType getDispatcherType() – 返回请求的分派器（dispatcher）类型。容器使用请求的分派器类型来选择需要应用到请求的过滤器。只有匹配分派器类型和 url 模式（url pattern）的过滤器才会被应用。允许一个过滤器配置多个分派器类型，过滤器可以根据请求的不同分派器类型处理请求。请求的初始分派器类型定义为 DispatcherType.REQUEST 。使用RequestDispatcher.forward(ServletRequest, ServletResponse) 或 RequestDispatcher.include(ServletRequest, ServletResponse) 分派时，它们的请求的分派器类型分别是 DispatcherType.FORWARD 或 DispatcherType.INCLUDE ，当一个异步请求使用任意一个AsyncContext.dispatch 方法分派时该请求的分派器类型是DispatcherType.ASYNC。最后，由容器的错误处理机制分派到错误页面的分派器类型是 DispatcherType.ERROR 。
    - AsyncContext – 该类表示在 ServletRequest 启动的异步操作执行上下文，AsyncContext 由之前描述的 ServletRequest.startAsync 创建并初始化。AsyncContext 的方法：
        -  public ServletRequest getRequest() – 返回调用 startAsync用于初始化 AsyncContext 的请求对象。当在异步周期之前调用了complete 或任意一个 dispatch 方法，调用 getRequest 将抛出IllegalStateException。
        -  public ServletResponse getResponse() –返回调用 startAsync 用于初始化 AsyncContext 的响应对象。当在异步周期之前调用了 complete 或任意一个 dispatch 方法，调用getResponse 将抛出 IllegalStateException。
        -  public void setTimeout(long timeoutMilliseconds) – 设置异步处理的超时时间，以毫秒为单位。该方法调用将覆盖容器设置的超时时间。如果没有调用 setTimeout 设置超时时间，将使用容器默认的超时时间。一个小于等于0的数表示异步操作将永不超时。当调用任意一个 ServletRequest.startAsync 方法时，一旦容器启动的分派返回到容器，超时时间将应用到 AsyncContext。当在异步周期开始时容器启动的分派已经返回到容器后，再设置超时时间是非法的，这将抛出一个 IllegalStateException 异常。
        -  public long getTimeout() – 获取 AsyncContext 关联的超时时间的毫秒值。该方法返回容器默认的超时时间，或最近一次调用setTimeout 设置超时时间。
        -  public void addListener(AsyncListener listener, ServletRequest req, ServletResponse res) – 注册一个用于接收的 onTimeout, onError, onComplete 或 onStartAsync 通知的监听器。前三个是与最近通过调用任意 ServletRequest.startAsync 方法启动的异步周期相关联的。onStartAsync 是与通过任意 ServletRequest.startAsync 启动的一个新的异步周期相关联的。异步监听器将以它们添加到请求时的顺序得到通知。当 AsyncListener 得到通知，传入到该方法的请求响应对象与 AsyncEvent.getSuppliedRequest() 和AsyncEvent.getSuppliedResponse() 是完全相同的。不应该对这些对象进行读取或写入，因为自从注册了 AsyncListener 后可能发生了额外的包装，不过可以被用来按顺序释放与它们关联的资源。容器启动的分派在异步周期启动后返回到容器后，或者在一个新的异步周期启动之前，调用该方法是非法的，将抛出IllegalStateException。
        -  public createListener(Class clazz) – 实例化指定的 AsyncListener 类。返回的AsyncListener 实例在使用下文描述的 addListener 方法注册到AsyncContext 之前可能需要进一步的自定义。给定的AsyncListener 类必须定义一个用于实例化的空参构造器，该方法支持适用于 AsyncListener 的所有注解。
        -  public void addListener(AsyncListener) – 注册给定的监听器用于接收 onTimeout, onError, onComplete 或 onStartAsync通知。前三个是与最近通过调用任意 ServletRequest.startAsync方法启动的异步周期相关联的。onStartAsync 是与通过任意ServletRequest.startAsync 启动的一个新的异步周期相关联的。异步监听器将以它们添加到请求时的顺序得到通知。当AsyncListener 接收到通知，如果在请求时调用 startAsync(req, res) 或 startAsync()，从 AsyncEvent 会得到同样的请求和响应对象。请求和响应对象可以是或者不是被包装的。异步监听器将以它们添加到请求时的顺序得到通知。容器启动的分派在异步周期启动后返回到容器后，或者在一个新的异步周期启动之前，调用该方法是非法的，将抛出 IllegalStateException。
        -  public void dispatch(String path) – 将用于初始化AsyncContext 的请求和响应分派到指定的路径的资源。该路径以相对于初始化 AsyncContext 的 ServletContext 进行解析。与请求查询方法相关的所有路径，必须反映出分派的目标，同时原始请求的URI，上下文，路径信息和查询字符串都可以从请求属性中获取，请求属性定义在9.7.2章节，“分派的请求参数”。这些属性必须反映最原始的路径元素，即使在多次分派之后。
        -  public void dispatch() – 一个简便方法，使用初始化AsyncContext 时的请求和响应进行分派，如下所示。 如果使用startAsync(ServletRequest, ServletResponse) 初始化AsyncContext，且传入的请求是 HttpServletRequest 的一个实例，则使用 HttpServletRequest.getRequestURI() 返回的 URI 进行分派。否则分派的是容器最后分派的请求 URI。下面的代码示例2-1，代码示例2-2和代码示例2-3演示了不同情况下分派的目标 URI是什么。
```
// REQUEST to /url/A
AsyncContext ac = request.startAsync();
...
ac.dispatch(); // ASYNC dispatch to /url/A
```
```
// REQUEST to /url/A
// FORWARD to /url/B
request.getRequestDispatcher(“/url/B”).forward(request,
response);
// Start async operation from within the target of the FORWARD
AsyncContext ac = request.startAsync();
ac.dispatch(); // ASYNC dispatch to /url/A
```

-  public void dispatch(ServletContext context, String path) -将用于初始化 AsyncContext 的请求和响应分派到指定ServletContext的指定路径的资源。
- 上面定义了 dispatch 方法的全部3个变体，调用这些方法且将请求和响应对象传入到容器的一个托管线程后将立即返回，在托管线程中异步操作将被执行。请求的分派器类型设置为异步（ASYNC）。不同于RequestDispatcher.forward(ServletRequest, ServletResponse) 分派，响应的缓冲区和头信息将不会重置，即使响应已经被提交分派也是合法的。控制委托给分派目标的请求和响应，除非调用了ServletRequest.startAsync() 或 ServletRequest.startAsync(ServletRequest, ServletResponse)，否则响应将在分派目标执行完成时被关闭。在调用了 startAsync 方法的容器启动的分派没有返回到容器之前任何dispatch方法的调用将没有任何作用。AsyncListener.onComplete(AsyncEvent), AsyncListener.onTimeout(AsyncEvent) 和AsyncListener.onError(AsyncEvent) 的调用将被延迟到容器启动的分派返回到容器之后。通过调用 ServletRequest.startAsync.启动的每个异步周期至多只有一个异步分派操作。相同的异步周期内任何试图执行其他的异步分派操作是非法的并将导致抛出IllegalStateException。如果后来在已分派的请求上调用startAsync，那么所有的 dispatch 方法调用将和之上具有相同的限制。
- 任何在执行 dispatch 方法期间可能抛出的错误或异常必须由容器抓住和处理，如下所示：
    - i. 调用所有由AsyncContext创建的并注册到ServletRequest的AsyncListener 实例的AsyncListener.onError(AsyncEvent) 方法， 可以通过AsyncEvent.getThrowable()获取到捕获的Throwable。
    - ii. 如果没有监听器调用 AsyncContext.complete 或任何AsyncContext.dispatch 方法，然后执行一个状态码为HttpServletResponse.SC_INTERNAL_SERVER_ERROR的出错分派，并且可以通过 RequestDispatcher.ERROR_EXCEPTION 请求属性获取 Throwable 值。
    - iii. 如果没有找到匹配的错误页面，或错误页面没有调用AsyncContext.complete() 或任何 AsyncContext.dispatch 方法，则容器必须调用 AsyncContext.complete。
        - public boolean hasOriginalRequestAndResponse() – 该方法检查AsyncContext 是否以原始的请求和响应对象调用ServletRequest.startAsync()完成初始化的，或者是否通过调用ServletRequest.startAsync(ServletRequest, ServletResponse)完成初始化的，且传入的ServletRequest 和ServletResponse 参数都不是应用提供的包装器，这样的话将返回true。如果AsyncContext 使用包装的请求及（或）响应对象调用ServletRequest.startAsync(ServletRequest, ServletResponse)完成初始化，那么将返回false。在请求处于异步模式后，该信息可以被出站方向调用的过滤器使用，用于决定是否在入站调用时添加的请求及（或）响应包装器需要在异步操作期间被维持或者被释放。
        - public void start(Runnable r) – 该方法导致容器分派一个线程，该线程可能来自托管的线程池，用于运行指定的 Runnable 对象。容器可能传播相应的上下文信息到该Runnable 对象。
        - public void complete() – 如果调用了 request.startAsync，则必须调用该方法以完成异步处理并提交和关闭响应。如果请求分派到一个不支持异步操作的 Servlet，或者由 AsyncContext.dispatch调用的目标 servlet 之后没有调用 startAsync，则 complete 方法会由容器调用。这种情况下，容器负责当 servlet 的 service 方法一退出就调用 complete()。 如果 startAsync 没有被调用则必须抛出IllegalStateException。在调用 ServletRequest.startAsync() 或 ServletRequest.startAsync(ServletRequest, ServletResponse) 之后且在调用任意 dispatch 方法之前的任意时刻调用 complete() 是合法的。在调用了 startAsync 方法的容器启动的分派没有返回到容器之前该方法的调用将没有任何作用。AsyncListener.onComplete(AsyncEvent) 的调用将被延迟到容器启动的分派返回到容器之后。
- ServletRequestWrapper
    - public boolean isWrapperFor(ServletRequest req)- 检查该包装器是否递归的包装了给定的 ServletRequest，如果是则返回 true，否则返回 false。
    - public boolean isWrapperFor(ServletRequest req)- 检查该包装器是否递归的包装了给定的 ServletRequest，如果是则返回 true，否则返回 false。
- ServletResponseWrapper
    - public boolean isWrapperFor(ServletResponse res)- 检查该包装器是否递归的包装了给定的 ServletResponse，如果是则返回 true，否则返回 false。
- AsyncListener
    - public void onComplete(AsyncEvent event) – 用于通知监听器在Servlet 上启动的异步操作完成了。
    - public void onError(AsyncEvent event) – 用于通知监听器异步操作未能完成。
    - public void onStartAsync(AsyncEvent event) – 用于通知监听器正在通过调用一个 ServletRequest.startAsync 方法启动一个新的异步周期。正在被重新启动的异步操作对应的 AsyncContext 可以通过调用给定的event 上调用 AsyncEvent.getAsyncContext 获取。
- 在异步操作超时的情况下，容器必须按照如下步骤运行：
    - 当异步操作启动后调用注册到 ServletRequest 的所有 AsyncListener 实例的 AsyncListener.onTimeout 方法。
    - 如果没有监听器调用 AsyncContext.complete() 或任何AsyncContext.dispatch 方法，执行一个状态码为HttpServletResponse.SC_INTERNAL_SERVER_ERROR 出错分派。
    - 如果没有找到匹配的错误页面，或者错误页面没有调用AsyncContext.complete() 或任何AsyncContext.dispatch 方法，则容器必须调用AsyncContext.complete()。
    - 如果在AsyncListener中调用方法抛出异常，将记录下来 且将不影响任何其他AsyncListener的调用。
    - 默认情况下是不支持 JSP 中的异步处理，因为它是用于内容生成且异步处理可能在内容生成之前已经完成。这取决于容器如何处理这种情况。一旦完成了所有的异步活动，使用 AsyncContext.dispatch 分派到的 JSP 页面可以用来生成内容。
    - 下面所示的图2-1描述了各种异步操作的状态转换。
    图2-1异步操作的状态转换图
 ![1异步操作的状态转换图](leanote://file/getImage?fileId=5ebfe7e302cf350962000002)

#### 线程安全

除了startAsync 和 complete 方法，请求和响应对象的实现都不保证线程安全。这意味着它们应该仅在请求处理线程范围内使用或应用确保线程安全的访问请求和响应对象。

如果应用使用容器管理对象创建一个线程，例如请求或响应对象，这些对象必须在其生命周期内被访问，就像定义在3.12节的“请求对象的生命周期”和5.7节的“响应对象的生产周期”。请注意，除了 startAsync 和complete 方法，请求和响应对象不是线程安全的。如果这些对象需要多线程访问，需要同步这些访问或通过包装器添加线程安全语义，比如，同步化调用访问请求属性的方法，或者在线程内为响应对象使用一个局部输出流。

#### 升级过程

在HTTP/1.1，Upgrade 通用头允许客户端指定其支持和希望使用的其他通信协议。如果服务器找到合适的切换协议，那么新的协议将在之后的通信中使用。Servle t容器提供了 HTTP 升级机制。不过，Servlet 容器本身不知道任何升级协议。协议处理封装在 HttpUpgradeHandler 协议处理器。在容器和 HttpUpgradeHandler 协议处理器之间通过字节流进行数据读取或写入。
<br>
当收到一个升级请求，servlet 可以调用 HttpServletRequest.upgrade方法启动升级处理。该方法实例化给定的 HttpUpgradeHandler 类，返回的 HttpUpgradeHandler 实例可以被进一步的定制。应用准备和发送一个合适的响应到客户端。退出 servlet service 方法之后，servlet 容器完成所有过滤器的处理并标记连接已交给 HttpUpgradeHandler 协议处理器处理。然后调用 HttpUpgradeHandler 协议处理器的 init 方法，传入一个 WebConnection 以允许 HttpUpgradeHandler 协议处理器访问数据流。
Servlet 过滤器仅处理初始的 HTTP 请求和响应，然后它们将不会再参与到后续的通信中。换句话说，一旦请求被升级，它们将不会被调用。
HttpUpgradeHandler 可以使用非阻塞 IO（non blocking IO）消费和生产消息。
当处理 HTTP 升级时，开发人员负责线程安全的访问 ServletInputStream 和 ServletOutputStream。
当升级处理已经完成，将调用 HttpUpgradeHandler.destroy 方法

#### 服务的终止

servlet 容器没必要保持装载的 servlet 持续任何特定的一段时间。一个 servlet 实例可能会在 servlet 容器内保持活跃（active）持续一段时间（以毫秒为单位），servlet容器的寿命可能是几天，几个月，或几年，或者是任何之间的时间。

当 servlet 容器确定 servlet 应该从服务中移除时，将调用 Servlet 接口的 destroy 方法以允许 servlet 释放它使用的任何资源和保存任何持久化的状态。例如，当想要节省内存资源或它被关闭时，容器可以做这个。


在 servlet 容器调用 destroy 方法之前，它必须让当前正在执行service 方法的任何线程完成执行，或者超过了服务器定义的时间限制。


一旦调用了 servlet 实例的 destroy 方法，容器无法再路由其他请求到该 servlet 实例了。如果容器需要再次使用该 servlet，它必须用该servlet 类的一个新的实例。在 destroy 方法完成后，servlet 容器必须释放 servlet 实例以便被垃圾回收。



# The Request(访问请求)
请求对象封装了来自客户端请求的所有信息。在HTTP协议中，此信息在HTTP标头和请求的消息正文中从客户端传输到服务器。
## HTTP 协议参数

Servlet的请求参数是客户端作为其请求的一部分发送到Servlet容器的字符串。当请求是HttpServletRequest对象，并且满足接下来的“当参数可用时”中列出的条件时，容器将根据URI查询字符串和POST-ed数据填充参数。

参数存储为一组名称/值对。对于任何给定的参数名称，可以存在多个参数值。ServletRequest接口的以下方法可用于访问参数：

- getParameter
- getParameterNames
- getParameterValues
- getParameterMap

getParameterValues 方法返回一个 String 对象的数组，包含了与参数名称相关的所有参数值。

getParameter 方法的返回值必须是getParameterValues 方法返回的 String 对象数组中的第一个值。

getParameterMap 方法返回请求参数的一个 java.util.Map 对象，其中以参数名称作为 map 键，参数值作为 map 值。

查询字符串和 POST 请求的数据被汇总到请求参数集合中。查询字符串数据放在 POST 数据之前。例如，如果请求由查询字符串 a=hello 和 POST 数据 a=goodbye&a=world 组成，得到的参数集合顺序将是 a=(hello, goodbye, world)。

这些 API 不会暴露 GET 请求（HTTP 1.1所定义的）的路径参数。他们必须从 getRequestURI 方法或 getPathInfo 方法返回的字符串值中解析。


## 当参数可用时

Post 表单数据能填充到参数集（Paramter Set）前必须满足的条件：

1. 该请求是一个 HTTP 或 HTTPS 请求。
2. HTTP 方法是 POST。
3. 内容类型是 application/x-www-form-urlencoded。
4. 该 servlet 已经对请求对象的任意 getParameter 方法进行了初始调用。

如果不满足这些条件，而且参数集中不包括 post 表单数据，那么 servlet 必须可以通过请求对象的输入流得到 post 数据。如果满足这些条件，那么从请求对象的输入流中直接读取 post 数据将不再有效。

## 文件上传

当数据以multipart/form-data的格式发送时，servlet 容器支持文件上传。
如果满足以下任何一个条件，servlet 容器提供 multipart/form-data格式数据的处理。

- servlet处理的请求使用了第8.1.5节定义的注解@MultipartConfig。
- 为了servlet处理请求，部署描述符包含了一个 multipart-config元素。

请求中的 multipart/form-data 类型的数据如何可用，取决于servlet 容器是否提供 multipart/form-data 格式数据的处理：

- 如果 servlet 容器提供 multipart/form-data 格式数据的处理，可通过 HttpServletRequest 中的以下方法得到：
    -  public Collection getParts()
    -  public Part getPart(String name) 
    
每个 part 都可通过 Part.getInputStream 方法访问头部，相关的内容类型和内容。 对于表单数据的 Content-Disposition，即使没有文件名，也可使用 part 的名称通过 HttpServletRequest 的 getParameter 和getParameterValues 方法得到 part 的字符串值。

- 如果 servlet 的容器不提供 multi-part/form-data 格式数据的处理，这些数据将可通过 HttpServletReuqest.getInputStream 得到。

## 属性

属性是与请求相关联的对象。属性可以由容器设置来表达信息，否则无法通过 API 表示，或者由 servlet 设置将信息传达给另一个 servlet（通过 RequestDispatcher）。属性通过 ServletRequest 接口中下面的方法来访问：


- getAttribute
- getAttributeNames
- setAttribute

一个属性名称只能关联一个属性值。

属性名称以java.前缀开头。和javax.保留供本规范定义。同样，以sun.，com.sun.，oracle和com.oracle前缀开头的属性名称保留给Oracle Corporation定义。建议根据[Java编程语言的规范](http://docs.oracle.com/javase/specs/)提出的用于包命名的反向域名约定来命名放置在属性集中的所有属性。

## 请求头

通过下面的 HttpServletRequest 接口方法，servlet 可以访问 HTTP 请求的头信息：


- getHeader
- getHeaders
- getHeaderNames

getHeader 方法返回给定头名称的头。多个头可以具有相同的名称，例如HTTP 请求中的 Cache-Control 头。如果多个头的名称相同，getHeader方法返回请求中的第一个头。 getHeaders 方法允许访问所有与特定头名称相关的头值，返回一个 String 对象的 Enumeration（枚举）。 头可包含由 String 形式的 int 或 Date 数据。HttpServletRequest接口提供如下方便的方法访问这些类型的头数据：


- getIntHeader
- getDateHeader

如果 getIntHeader 方法不能转换为 int 的头值，则抛出NumberFormatException 异常。如果 getDateHeader 方法不能把头转换成一个 Date 对象，则抛出 IllegalArgumentException 异常。



## 请求路径元素

引导 servlet 服务请求的请求路径由许多重要部分组成。以下元素从请求URI路径得到，并通过请求对象公开：

- Context Path：与ServletContext相关联的路径前缀是这个Servlet 的一部分。如果这个上下文是基于Web服务器的URL命名空间基础上的“默认”上下文，那么这个路径将是一个空字符串。否则，如果上下文不是基于服务器的命名空间，那么这个路径以“/”字符开始，但不以“/”字符结束。
- Servlet Path：路径部分直接与激活请求的映射对应。这个路径以“/”字符开头，如果请求与“/*”或“”模式匹配，在这种情况下，它是一个空字符串。
- PathInfo：请求路径的一部分，不属于Context Path或Servlet Path。如果没有额外的路径，它要么是null，要么是以“/”开头的字符串。

使用 HttpServletRequest 接口中的下面方法来访问这些信息：

- getContextPath
- getServletPath
- getPathInfo

```
requestURI = contextPath + servletPath + pathInfo

```

举几个例子来解析上述各点，请考虑以下几点：
TABLE 3-1 Example Context Set Up

Context Path|/catalog 
-|-
Servlet Mapping|Pattern: Servlet:/lawn/* LawnServlet
Servlet Mapping|Pattern: Servlet:/garden/* GardenServlet
Servlet Mapping|Pattern: Servlet:*.jsp JSPServlet


遵守下列行为：
TABLE 3-2 Observed Path Element Behavior

请求路径|	路径元素
-|-
/catalog/lawn/index.html|	ContextPath: /catalog<br> ServletPath: /lawn <br> PathInfo: /index.html
/catalog/garden/implements/	|ContextPath: /catalog<br>  ServletPath: /garden <br> PathInfo: /implements/
/catalog/help/feedback.jsp|	ContextPath: /catalog<br>  ServletPath: /help/feedback.jsp<br>  PathInfo: null

## 路径转换方法

在 API 中有两个方便的方法，允许开发者获得与某个特定的路径等价的文件系统路径。这些方法是：


- ServletContext.getRealPath
- HttpServletRequest.getPathTranslated

 getRealPath 方法需要一个 String 参数，并返回一个 String 形式的路径，这个路径对应一个在本地文件系统上的文件 getPathTranslated 方法推断出请求的 pathInfo 的实际路径。 这些方法在 servlet 容器无法确定一个有效的文件路径 的情况下，如 Web应用程序从归档中，在不能访问本地的远程文件系统上，或在一个数据库中执行时，这些方法必须返回 null。JAR 文件中 META-INF/resources 目录下的资源，只有当调用 getRealPath()  方法时才认为容器已经从包含它的 JAR 文件中解压，在这种情况下，必须返回解压缩后位置。

译者注：
方法|结果
-|-
URL|http://127.0.0.1:8080/test/test2?a=2342
getServletPath|/test
getContextPath|""
getPathInfo|/test2
getPathTranslated()|/private/var/folders/t1/czp1j7dx6kz5c_25_2glbfw40000gn/T/undertow-docbase.2053444027420375252.8080/test2
getRealPath("/")|/private/var/folders/t1/czp1j7dx6kz5c_25_2glbfw40000gn/T/undertow-docbase.2053444027420375252.8080

## 非阻塞 IO

Web 容器中的非阻塞请求处理有助于提高对改善 Web 容器可扩展性不断增加的需求，增加 Web 容器可同时处理请求的连接数量。servlet 容器的非阻塞 IO 允许开发人员在数据可用时读取数据或在数据可写时写数据。非阻塞 IO 仅对在 Servlet 和 Filter（2.3.3.3节定义的，“异步处理”）中的异步请求处理和升级处理（2.3.3.5节定义的，“升级处理”）有效。否则，当调用 ServletInputStream.setReadListener 或ServletOutputStream.setWriteListener 方法时将抛出IllegalStateException。

ReadListener 为非阻塞IO提供了下面的回调方法：

- ReadListener
    - onDataAvailable().当可以从传入的请求流中读取数据时ReadListener 的 onDataAvailable 方法被调用。当数据可读时容器初次调用该方法。当且仅当下面描述的 ServletInputStream 的isReady 方法返回 false，容器随后将调用 onDataAvailable 方法。
    - onAllDataRead().当读取完注册了此监听器的 ServletRequest 的所有数据时调用 onAllDataRead 方法。
    - onError(Throwable t). 处理请求时如果有任何错误或异常发生时调用 onError 方法。
    
容器必须线程安全的访问 ReadListener 中的方法。

除了上述 ReadListener 定义的方法外，下列方法已被添加到ServletInputStream 类中：

- ServletInputStream
    - boolean isFinished(). ServletInputStream 相关的请求的所有数据已经读取完时 isFinished 方法返回 true。否则返回 false。
    - boolean isReady().如果可以无阻塞地读取数据 isReady 方法返回 true。如果没有数据可以无阻塞地读取该方法返回 false。如果isReady 方法返回 false，调用 read 方法是非法的，且必须抛出IllegalStateException。
    - void setReadListener(ReadListener listener). 设置上述定义的 ReadListener，调用它以非阻塞的方式读取数据。一旦把监听器与给定的 ServletInputStream 关联起来，当数据可以读取，所有的数据都读取完或如果处理请求时发生错误，容器调用 ReadListener 的方法。注册一个 ReadListener 将启动非阻塞 IO。在那时切换到传统的阻塞IO是非法的，且必须抛出 IllegalStateException。在当前请求范围内，随后调用 setReadListener 是非法的且必须抛出IllegalStateException。

## http2 服务器推送

服务器推送是显示在Servlet API中的HTTP / 2改进中最明显的。HTTP / 2中的所有新功能（包括服务器推送）均旨在改善Web浏览体验的感知性能。服务器推入是基于这样一个简单的事实，即服务器处于比客户端更好的位置上，以了解初始请求会附带哪些其他资产（例如图像，样式表和脚本），这有助于提高浏览器的感知性能。例如，服务器可能知道，每当浏览器请求index.html时，此后不久它将请求header.gif，footer.gif和style.css。由于服务器知道这一点，因此它们可以抢先开始将这些资产的字节与index.html的字节一起发送。

<br>
要使用服务器推送，请从HttpServletRequest获取对PushBuilder的引用，根据需要更改生成器，然后调用push（）。请参阅javadoc中有关方法javax.servlet.http.HttpServletRequest.newPushBuilder（）的信息，以及有关类javax.servlet.http.PushBuilder的规范。本节的其余部分针对第vi页“其他重要参考”中引用的HTTP / 2规范版本中标题为“服务器推送”的部分，提出了实现要求。

<br>

除非明确排除，否则Servlet 4.0容器必须支持HTTP/2规范部分“服务器推送”中指定的服务器推送。如果客户端能够使用HTTP/2，则容器必须启用服务器推送，除非客户端通过为当前连接发送SETTINGS_ENABLE_PUSH设置值0（零）来显式禁用服务器推送。在这种情况下，仅对于该连接，不得启用服务器推送。

除了允许客户端使用SETTINGS_ENABLE_PUSH设置来禁用服务器推送外，Servlet容器还必须通过注意引用已推送流的流标识符的CANCEL或REFUSED_STREAM代码来满足客户端的请求，以更细粒度地不接收推送响应。这种交互的一种常见用法是当浏览器的缓存中已有资源时。


##Cookie

HttpServletRequest 接口提供了 getCookies 方法来获得请求中的cookie 的一个数组。这些 cookie 是从客户端发送到服务器端的客户端发出的每个请求上的数据。典型地，客户端发送回的作为 cookie 的一部分的唯一信息是 cookie 的名称和 cookie 值。当 cookie 发送到浏览器时可以设置其他 cookie 属性，诸如注释，这些信息不会返回到服务器。该规范还允许的 cookies 是 HttpOnly cookie。HttpOnly cookie 暗示客户端它们不会暴露给客户端脚本代码（它没有被过滤掉，除非客户端知道如何查找此属性）。使用 HttpOnly cookie 有助于减少某些类型的跨站点脚本攻击。

## SSL 属性

如果请求已经是通过一个安全协议发送，如 HTTPS，必须通过ServletRequest 接口的 isSecure 方法公开该信息。Web 容器必须公开下列属性给 servlet 程序员：
TABLE 3-3 Protocol Attributes

属性|	属性名称|	Java类型
-|-|-
密码套件|	javax.servlet.request.cipher_suite|	String
算法的位大小|	javax.servlet.request.key_size|	Integer
SSL 会话 id|	javax.servlet.request.ssl_session_id|	String

如果有一个与请求相关的 SSL 证书，它必须由 servlet 容器以java.security.cert.X509Certificate 类型的对象数组暴露给servlet 程序员并可通过一个javax.servlet.request.X509Certificate 类型的 ServletRequest属性访问。
这个数组的顺序是按照信任的升序顺序。证书链中的第一个证书是由客户端设置的，第二个是用来验证第一个的，等等。

## 国际化

客户可以选择希望 Web 服务器用什么语言来响应。该信息可以和使用Accept-Language 头与 HTTP/1.1 规范中描述的其他机制的客户端通信。ServletRequest 接口提供下面的方法来确定发送者的首选语言环境：

- getLocale
- getLocales

getLocale 方法将返回客户端要接受内容的首选语言环境。要了解更多关于 Accept-Language 头必须被解释为确定客户端首选语言的信息，请参阅 RFC 2616（HTTP/1.1）14.4节。
getLocales 方法将返回一个Locale 对象的 Enumeration (枚举)，从首选语言环境开始顺序递减，这些语言环境是可被客户接受的语言环境。 如果客户端没有指定首选语言环境，getLocale 方法返回的语言环境必须是 servlet 容器默认的语言环境，而 getLocales 方法必须返回只包含一个默认语言环境的 Local 元素的枚举。

## Request数据编码

目前，许多浏览器不随着 Content-Type 头一起发送字符编码限定符，而是根据读取 HTTP 请求确定字符编码。如果客户端请求没有指定请求默认的字符编码，容器用来创建请求读取器和解析 POST 数据的编码必须是“ISO-8859-1”。然而，为了向开发人员说明客户端没有指定请求默认的字符编码，在这种情况下，客户端发送字符编码失败，容器从getCharacterEncoding 方法返回 null。

如果客户端没有设置字符编码，并使用不同的编码来编码请求数据，而不是使用上面描述的默认的字符编码，那么可能会发生问题。为了弥补这种情况，ServletRequest 接口添加了一个新的方法 setCharacterEncoding(String enc)。开发人员可以通过调用此方法来覆盖由容器提供的字符编码。必须在解析任何 post 数据或从Request读取任何输入之前调用此方法。此方法一旦调用，将不会影响已经读取的数据的编码。

## Request对象生命周期

每个Request对象只在一个 servlet 的 service 方法的作用域内，或过滤器的 doFilter 方法的作用域内有效，除非该组件启用了异步处理并且调用了请求对象的 startAsync 方法。在发生异步处理的情况下，请求对象一直有效，直到调用 AsyncContext 的 complete 方法。容器通常会重复利用Request对象，以避免创建请求对象而产生的性能开销。开发人员必须注意的是，不建议在上述范围之外保持 startAsync 方法还没有被调用的请求对象的引用，因为这样可能产生不确定的结果。
在升级情况下，如上描述仍成立。




# <a id="context">Servlet Context(Servlet上下文)</a>

## ServletContext 接口介绍

ServletContext 接口定义了 servlet 运行在的 Web 应用的视图。容器供应商负责提供 servlet 容器的 ServletContext 接口的实现。servlet 可以使用 ServletContext 对象记录事件，获取 URL 引用的资源，存取当前上下文的其他 servlet 可以访问的属性。
ServletContext 是 Web 服务器中已知路径的根。例如，servlet 上下文可以从 http://www.waylau.com/catalog 找出，/catalog 请求路径称为上下文路径，所有以它开头的请求都会被路由到与 ServletContext 相关联的 Web 应用。

## ServletContext 接口作用域

每一个部署到容器的 Web 应用都有一个 ServletContext 接口的实例与之关联。在容器分布在多台虚拟机的情况下，每个 JVM 的每个 Web 应用将有一个 ServletContext 实例。
如果容器内的 Servlet 没有部署到 Web 应用中，则隐含的作为“默认” Web 应用的一部分，并有一个默认的 ServletContext 。在分布式的容器中，默认的 ServletContext 是非分布式的且仅存在于一个 JVM 中。

## 初始化参数

如下 ServletContext 接口方法允许 servlet 访问由应用开发人员在Web 应用中的部署描述符中指定的上下文初始化参数：

- getInitParameter
- getInitParameterNames

应用开发人员使用初始化参数来表达配置信息。代表性的例子是一个网络管理员的 e-mail 地址，或保存关键数据的系统名称。

## 配置方法

下面的方法从 Servlet 3.0 开始添加到 ServletContext，以便启用编程方式定义 Servlet、Filter 和它们映射到的 url 模式。这些方法只能从 ServletContextListener 实现的 contexInitialized 方法或者 ServletContainerInitializer 实现的 onStartup方法进行的应用初始化过程中调用。 除了添加 Servlet 和 Filter，也可以查找关联到Servlet 或 Filter 的一个 Registration 对象实例，或者到 Servlet 或 Filter 的所有 Registration 对象的 map。 如果 ServletContext 传到了 ServletContextListener 的 contextInitialized 方法，但该 ServletContextListener 即没有在 web.xml 或 web-fragment.xml 中声明也没有使用 @WebListener 注解，则在 ServletContext 中定义的用于 Servlet、Filter 和 Listener 的编程式配置的所有方法必须抛出UnsupportedOperationException。

### 编程式添加和配置 Servlet

编程式添加 Servlet 到上下文对框架开发者是很有用的。例如，框架可以使用这个方法声明一个控制器 servlet。这个方法将返回一个ServletRegistration 或 ServletRegistration.Dynamic 对象，允许我们进一步配置如 init-params，url-mapping 等 Servlet 的其他参数。下面描述了该方法的三个重载版本。

#### addServlet(String servletName, String className)

该方法允许应用以编程方式声明一个 servlet。它添加给定的servlet名称和class名称到 servlet 上下文

#### addServlet(String servletName, Servlet servlet)

该方法允许应用以编程方式声明一个 Servlet。它添加给定的名称和Servlet 实例的 Servlet 到 servlet 上下文。

####  addServlet(String servletName, Class &lt;? extends Servlet &gt; servletClass)

该方法允许应用以编程方式声明一个 Servlet。它添加给定的名称和Servlet 类的一个实例的 Servlet 到 servlet 上下文

#### T createServlet(Class clazz)
该方法实例化一个给定的 Servlet class，该方法必须支持适用于Servlet 的除了 @WebServlet 的所有注解。 返回的 Servlet 实例通过调用上边定义的 addServlet(String, Servlet) 注册到 ServletContext 之前，可以进行进一步的定制。

#### ServletRegistration getServletRegistration(String servletName)

该方法返回与指定名字的 Servlet 相关的 ServletRegistration，或者如果没有该名字的 ServletRegistration 则返回 null。如果ServletContext 传到了 ServletContextListener 的contextInitialized 方法，但该 ServletContextListener 即没有在web.xml或web-fragment.xml 中声明也没有使用javax.servlet.annotation.WebListener 注解，则必须抛出UnsupportedOperationException。

#### Map getServletRegistrations()

该方法返回 ServletRegistration 对象的 map，由名称作为键并对应着注册到 ServletContext 的所有 Servlet。如果没有 Servlet 注册到ServletContext 则返回一个空的 map。返回的 Map 包括所有声明和注解的 Servlet 对应的 ServletRegistration 对象，也包括那些使用addServlet 方法添加的所有 Servlet 对于的 ServletRegistration 对象。返回的 Map 的任何改变不影响 ServletContext。如果ServletContext 传到了 ServletContextListener 的contextInitialized 方法，但该 ServletContextListener 即没有在web.xml 或 web-fragment.xml 中声明也没有使用javax.servlet.annotation.WebListener 注解，则必须抛出UnsupportedOperationException。

### 编程式添加和配置 Filter

#### addFilter(String filterName, String className)

该方法允许应用以编程方式声明一个 Filter。它添加以给定的名称和class 名称的 Filter 到 web 应用。

####  addFilter(String filterName, Filter filter)

该方法允许应用以编程方式声明一个 Filter。它添加以给定的名称和filter 实例的 Filter 到 web 应用。
####  addFilter(String filterName, Class &lt;? extends Filter &gt; filterClass)

该方法允许应用以编程方式声明一个 Filter。它添加以给定的名称和filter 类的一个实例的 Filter 到 web 应用。
T createFilter(Class clazz)
该方法实例化一个给定的 Filter class，该方法必须支持适用于 Filter的所有注解。
返回的 Filter 实例通过调用上边定义的 ####  addServlet(String, Filter)注册到 ServletContext 之前，可以进行进一步的定制。给定的 Filter 类必须定义一个用于实例化的空参构造器。

####  FilterRegistration getFilterRegistration(String filterName)

该方法返回与指定名字的 Filter 相关的 FilterRegistration，或者如果没有该名字的 FilterRegistration 则返回 null。如果ServletContext 传到了 ServletContextListener 的contextInitialized 方法，但该 ServletContextListener 即没有在web.xml 或 web-fragment.xml 中声明也没有使用javax.servlet.annotation.WebListener 注解，则必须抛出UnsupportedOperationException。

####  Map getFilterRegistrations()
该方法返回 FilterRegistration 对象的 map，由名称作为键并对应着注册到 ServletContext 的所有 Filter。如果没有 Filter 注册到ServletContext 则返回一个空的 Map。返回的 Map 包括所有声明和注解的 Filter 对应的 FilterRegistration 对象，也包括那些使用addFilter 方法添加的所有 Servlet 对于的 ServletRegistration 对象。返回的 Map 的任何改变不影响 ServletContext。如果ServletContext 传到了 ServletContextListener 的contextInitialized 方法，但该 ServletContextListener 即没有在web.xml 或 web-fragment.xml 中声明也没有使用javax.servlet.annotation.WebListener 注解，则必须抛出UnsupportedOperationException。

### 编程式添加和配置 Listener

#### void addListener(String className)

往 ServletContext 添加指定 class 名称的监听器。ServletContext 将使用由与应用关联的 classloader 装载加载该给定名称的 class，且它们必须实现一个或多个以下接口：

- javax.servlet.ServletContextAttributeListener
- javax.servlet.ServletRequestListener
- javax.servlet.ServletRequestAttributeListener
- javax.servlet.http.HttpSessionListener
- javax.servlet.http.HttpSessionAttributeListener
- javax.servlet.http.HttpSessionIdListener

如果 ServletContext 传到了 ServletContainerInitializer 的onStartup 方法，则给定名字的类可以实现除上面列出的接口之外的javax.servlet.ServletContextListener。作为该方法调用的一部分，容器必须装载指定类名的 class，以确保其实现了所需的接口之一。如果给定名字的类实现了一个监听器接口，则其调用顺序和声明顺序是一样的，换句话说，如果它实现了 javax.servlet.ServletRequestListener 或 javax.servlet.http.HttpSessionListener，那么新的监听器将被添加到该接口的有序监听器列表的末尾。

#### void addListener(T t)

往 ServletContext 添加一个给定的监听器。给定的监听器实例必须实现一个或多个如下接口：

- javax.servlet.ServletContextAttributeListener
- javax.servlet.ServletRequestListener
- javax.servlet.ServletRequestAttributeListener
- javax.servlet.http.HttpSessionListener
- javax.servlet.http.HttpSessionAttributeListener
- javax.servlet.http.HttpSessionIdListener

如果 ServletContext 传到了 ServletContainerInitializer 的onStartup 方法，则给定的监听器实例可以实现除上面列出的接口之外的javax.servlet.ServletContextListener。如果给定的监听器实例实现了一个监听器接口，则其调用顺序和声明顺序是一样的，换句话说，如果它实现了 javax.servlet.ServletRequestListener 或 javax.servlet.http.HttpSessionListener，那么新的监听器将被添加到该接口的有序监听器列表的末尾。

### void addListener(Class &lt;? extends EventListener&gt; listenerClass)

往 ServletContext 添加指定 class 类型的监听器。给定的监听器类必须实现是一个或多个如下接口：

- javax.servlet.ServletContextAttributeListener
- javax.servlet.ServletRequestListener
- javax.servlet.ServletRequestAttributeListener
- javax.servlet.http.HttpSessionListener
- javax.servlet.http.HttpSessionAttributeListener
- javax.servlet.http.HttpSessionIdListener

如果 ServletContext 传到了 ServletContainerInitializer 的onStartup 方法，则给定的监听器类可以实现除上面列出的接口之外的javax.servlet.ServletContextListener。如果给定的监听器类实现了一个监听器接口，则其调用顺序和声明顺序是一样的，换句话说，如果它实现了 javax.servlet.ServletRequestListener 或 javax.servlet.http.HttpSessionListener，那么新的监听器将被添加到该接口的有序监听器列表的末尾。

### void createListener(Class clazz)

该方法实例化给定的 EventListener 类。指定的 EventListener 类必须实现至少一个如下接口：

- javax.servlet.ServletContextAttributeListener
- javax.servlet.ServletRequestListener
- javax.servlet.ServletRequestAttributeListener
- javax.servlet.http.HttpSessionListener
- javax.servlet.http.HttpSessionAttributeListener
- javax.servlet.http.HttpSessionIdListener

该方法必须支持该规范定义的适用于如上接口的所有注解。返回的EventListener 实例可以在通过调用 addListener(T t) 注册到ServletContext 之前进行进一步的定制。给定的 EventListener 必须定义一个用于实例化的空参构造器。


#### 用于编程式添加 Servlet、Filter 和 Listener 的注解处理需求

除了需要一个实例的 addServlet 之外，当使用编程式API添加Servlet或创建Servlet时，以下类中的有关的注解必须被内省且其定义的元数据必须被使用，除非它被 ServletRegistration.Dynamic / ServletRegistration 中调用的API覆盖了。
@ServletSecurity、@RunAs、@DeclareRoles、@MultipartConfig。
对于 Filter 和 Listener 来说，不需要使用注解来内省。 除了通过需要一个实例的方法添加的那些组件，编程式添加或创建的所有组件（Servlet，Filter和Listener）上的资源注入，只有当组件是一个CDI Managed Bean时才被支持。进一步了解更多细节请参考15.5.15，“JavaEE 要求的上下文和依赖注入”。

### 以编程方式配置会话超时

ServletContext接口的以下方法允许Web应用程序访问和配置给定Web应用程序中创建的所有会话的默认会话超时间隔。setSessionTimeout中指定的超时以分钟为单位。如果超时等于或小于0，则容器将确保会话的默认行为永远不会超时。

-  getSessionTimeout()
- setSessionTimeout(int timeout)

### 以编程方式配置字符编码

ServletContext接口的以下方法允许Web应用程序访问和配置请求和响应字符编码。

- getRequestCharacterEncoding()
- setRequestCharacterEncoding(String encoding)
- getResponseCharacterEncoding()
- setResponseCharacterEncoding(String encoding)

如果在部署描述符或特定于容器的配置中（对于容器中的所有Web应用程序）未指定请求字符编码，则getRequestCharacterEncoding（）返回null。如果在部署描述符或特定于容器的配置中（对于容器中的所有Web应用程序）未指定响应字符编码，则getResponseCharacterEncoding（）返回null。


## 上下文属性

servlet 可以通过名字将对象属性绑定到上下文。同一个 Web 应用内的其他任何 servlet 都可以使用绑定到上下文的任意属性。以下 ServletContext 接口中的方法允许访问此功能：


- setAttribute
- getAttribute
- getAttributeNames
- removeAttribute

### 分布式容器中的上下文属性

在 JVM 中创建的上下文属性是本地的，这可以防止从一个分布式容器的共享内存存储中获取 ServletContext 属性。当需要在运行在分布式环境的Servlet 之间共享信息时，该信息应该被放到会话中（请看第7章，“会话”），或存储到数据库，或者设置到 Enterprise JavaBeans™ （企业级 JavaBean）组件。

## 资源

ServletContext 接口提供了直接访问 Web 应用中仅是静态内容层次结构的文件的方法，包括 HTML，GIF 和 JPEG 文件：

- getResource
- getResourceAsStream

getResource 和 getResourceAsStream 方法需要一个以 “/” 开头的String 作为参数，给定的资源路径是相对于上下文的根，或者相对于 web应用的 WEB-INF/lib 目录下的 JAR 文件中的 META-INF/resources 目录。这两个方法首先根据请求的资源查找 web 应用上下文的根，然后查找所有 WEB-INF/lib 目录下的 JAR 文件。查找 WEB-INF/lib 目录中 JAR文件的顺序是不确定的。这种层次结构的文件可以存在于服务器的文件系统，Web 应用的归档文件，远程服务器，或在其他位置。
这两个方法不能用于获取动态内容。例如，在支持 JavaServer Pages™规范的容器中，如 getResource("/index.jsp") 形式的方法调用将返回 JSP 源码而不是处理后的输出。请看第9章，“分发请求”获取更多关于动态内容的信息。 可以使用 getResourcePaths(String path) 方法访问 Web 应用中的资源的完整列表。该方法的语义的全部细节可以从本规范的 API 文档中找到。


## 多主机和 Servlet 上下文

Web 服务器可以支持多个逻辑主机共享一个服务器 IP 地址。有时，这种能力被称为“虚拟主机”。这种情况下，每一个逻辑主机必须有它自己的 servlet 上下文或一组 servlet 上下文。servlet 上下文不会在虚拟主机之间共享。


ServletContext 接口的 getVirtualServerName 方法允许访问ServletContext 部署在的逻辑主机的配置名字。该方法必须对所有部署在逻辑主机上的所有 servlet 上下文返回同一个名字。且该方法返回的名字必须是明确的、每个逻辑主机稳定的、和适合用于关联服务器配置信息和逻辑主机。

## 重载注意事项


尽管 Container Provider (容器供应商)不需要实现类的重加载模式以便易于开发，但是任何此类的实现必须确保所有 servlet 及它们使用的类（Servlet使用的系统类异常可能使用的是一个不同的 class loader）在一个单独的 class loader 范围内被加载。为了保证应用像开发人员预期的那样工作，该要求是必须的。作为一个开发辅助，容器应支持到会话绑定到的监听器的完整通知语义以用于当 class 重加载时会话终结的监控。
之前几代的容器创建新的 class loader 来加载 servlet，且与用于加载在 servlet 上下文中使用的其他 servlet 或类的 class loader 是完全不同的。这可能导致 servlet 上下文中的对象引用指向意想不到的类或对象，并引起意想不到的行为。为了防止因创建新的 class loader 所引起的问题，该要求是必须的。


### 临时工作目录

每一个 servlet 上下文都需要一个临时的存储目录。servlet 容器必须为每一个 servlet 上下文提供一个私有的临时目录，并将通过javax.servlet.context.tempdir 上下文属性使其可用，关联该属性的对象必须是 java.io.File 类型。
该要求公认为在多个 servlet 引擎实现中提供一个通用的便利。当 servlet 容器重启时，它不需要去保持临时目录中的内容，但必须确保一个 servlet 上下文的临时目录中的内容对运行在同一个 servlet 容器的其他 Web 应用的上下文不可见。

# The Response(访问响应)

响应对象封装了从服务器返回到客户端的所有信息。在HTTP协议中，这些信息是包含在从服务器传输到客户端的HTTP头信息或响应的消息体中。

## 缓存
出于性能的考虑，servlet 容器允许（但不要求）缓存输出到客户端的内容。一般的，服务器是默认执行缓存，但应该允许 servlet 来指定缓存参数。
下面是 ServletResponse 接口允许 servlet 来访问和设置缓存信息的方法：

- getBufferSize
- setBufferSize
- isCommitted
- reset
- resetBuffer
- flushBuffer

不管 servlet 使用的是一个 ServletOutputStream 还是一个 Writer，ServletResponse 接口提供的这些方法允许执行缓冲操作。 getBufferSize 方法返回使用的底层缓冲区大小。如果没有使用缓冲，该方法必须返回一个 int 值 0。 Servlet 可以请求 setBufferSize 方法设置一个最佳的缓冲大小。不一定分配 servlet 请求大小的缓冲区，但至少与请求的大小一样大。这允许容器重用一组固定大小的缓冲区，如果合适，可以提供一个比请求时更大的缓冲区。该方法必须在使用 ServletOutputStream 或 Writer 写任何内容之前调用。如果已经写了内容或响应对象已经提交，则该方法必须抛出IllegalStateException。
isCommitted 方法返回一个表示是否有任何响应字节已经返回到客户端的boolean 值。flushBuffer 方法强制刷出缓冲区的内容到客户端。 当响应没有提交时，reset方法清空缓冲区的数据。头信息，状态码和在调用 reset 之前 servlet 调用 getWriter 或 getOutputStream 设置的状态也必须被清空。如果响应没有被提交，resetBuffer 方法将清空缓冲区中的内容，但不清空请求头和状态码。
如果响应已经提交并且 reset 或 resetBuffer 方法已被调用，则必须抛出 IllegalStateException，响应及它关联的缓冲区将保持不变。
当使用缓冲区时，容器必须立即刷出填满的缓冲区内容到客户端。如果这是最早发送到客户端的数据，且认为响应被提交了。

## 头
servlet 可以通过下面 HttpServletResponse 接口的方法来设置 HTTP 响应头：

- setHeader
- addHeader

setHeader 方法通过给定的名字和值来设置头。前面的头会被后来的新的头替换。如果已经存在同名的头集合的值，集合中的值会被清空并用新的值替换。

addHeader 方法使用给定的名字添加一个头值到集合。如果没有头与给定的名字关联，则创建一个新的集合。



头可能包含表示 int 或 Date 对象的数据。以下 HttpServletResponse接口提供的便利方法允许 servlet 对适当的数据类型用正确的格式设置一个头：

- setIntHeader
- setDateHeader
- addIntHeader
- addDateHeader

为了成功传送回客户端，必须在提交响应之前设置标头（而不是trailer）。提交响应后设置的标头（不是trailer）将被servlet容器忽略。如果要在响应中发送RFC 7230中指定的HTTP trailer，则必须使用HttpServletResponse上的setTrailerFields（）方法提供它们。在编写分块响应中的最后一个分块之前，必须已调用此方法。Servlet程序员负责确保在响应对象中为Servlet生成的内容正确设置Content-Type标头。HTTP 1.1规范不需要在HTTP响应中设置此标头。当Servlet程序员未设置默认内容类型时，Servlet容器不得设置默认内容类型。建议容器使用X-Powered-By HTTP标头发布其实现信息。该字段值应包含一种或多种实现类型，例如“ Servlet / 4.0”。可选地，可以在括号内的实现类型之后添加容器和基础Java平台的补充信息。容器应可配置为禁止显示此标头。

Here’s the examples of this header.

```
X-Powered-By: Servlet/4.0
X-Powered-By: Servlet/4.0 JSP/2.3 (GlassFish Server Open Source Edition 5.0 Java/Oracle Corporation/1.8)
```

## HTTP Trailer


HTTP Trailer 是响应主体之后的一种特殊的HTTP标头的集合。Trailer在RFC 7230中指定。它们在分块传输编码的上下文中以及在其他通信协议的实现中很有用。Servlet容器为Trailer提供支持。

如果预告片标头已准备好读取，则isTrailerFieldsReady（）将返回true。然后，Servlet可以通过HttpServletRequest接口的getTrailerFields（）方法读取HTTP请求的尾标头。


通过向HttpServletResponse接口的setTrailerFields方法提供Supplier，Servlet可以将尾部报头写入响应。可以通过访问HttpServletResponse接口的getTrailerFields（）方法来获取。

有关规范规范的这两种方法，请参见javadoc。
译者注：参考 [Trailer - HTTP | MDN](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Trailer)

##  非阻塞 IO

非阻塞 IO 仅对在 Servlet 和 Filter（2.3.3.3节定义的，“异步处理”）中的异步请求处理和升级处理（2.3.3.5节定义的，“升级处理”）有效。否则，当调用 ServletInputStream.setReadListener 或ServletOutputStream.setWriteListener 方法时将抛出IllegalStateException。为了支持在 Servlet 容器中的非阻塞写，除了在3.7节描述的“非阻塞IO” 对 ServletRequest 做的更改之外，下面做出的更改以便于处理响应相关的类/接口。

WriteListener提供了如下适用于容器调用的回调方法。

- WriteListener
    - void onWritePossible().当一个 WriteListener注册到ServletOutputStream时，当可以写数据时该方法将被容器首次调用。当且仅当下边描述的ServletOutputStream的isReady 方法返回false，容器随后将调用该方法。
    - onError(Throwable t).当处理响应过程中出现错误时回调。 除了WriteListener外，还有如下方法被添加到ServletOutputStream类并允许开发人员运行时检查是否可以
- ServletOutputStream
    - boolean isReady(). 如果往 ServletOutputStream 写会成功，则该方法返回 true，其他情况会返回 false。如果该方法返回 true，可以在 ServletOutputStream 上执行写操作。如果没有后续的数据能写到 ServletOutputStream，那么直到底层的数据被刷出之前该方法将一直返回 false。且在此时容器将调用 WriteListener 的onWritePossible 方法。随后调用该方法将返回 true。
    - void setWriteListener(WriteListener listener). 关联WriteListener 和当且的ServletOutputStream，当ServletOutputStream可以写入数据时容器会调用WriteListener的回调方法。注册了 WriteListener 将开始非阻塞IO。此时再切换到传统的阻塞 IO 是非法的。


容器必须线程安全的访问WriteListener 中的方法。

## 简便方法

HttpServletResponse提供了如下简便方法：

- sendRedirect
- sendError 
sendRedirect方法将设置适当的头和内容体将客户端重定向到另一个地址。使用相对 URL 路径调用该方法是合法的，但是底层的容器必须将传回到客户端的相对地址转换为全路径 URL。无论出于什么原因，如果给定的URL是不完整的，且不能转换为一个有效的URL，那么该方法必须抛出IllegalArgumentException。


sendError方法将设置适当的头和内容体用于返回给客户端返回错误消息。可以 sendError 方法提供一个可选的 String 参数用于指定错误的内容体。
如果响应已经提交并终止，这两个方法将对提交的响应产生负作用。这两个方法调用后 servlet 将不会产生到客户端的后续的输出。这两个方法调用后如果有数据继续写到响应，这些数据被忽略。 如果数据已经写到响应的缓冲区，但没有返回到客户端（例如，响应没有提交），则响应缓冲区中的数据必须被清空并使用这两个方法设置的数据替换。如果响应已提交，这两个方法必须抛出 IllegalStateException。

## 国际化

Servlet 应设置响应的 locale和字符集。使用ServletResponse.setLocale方法设置locale。该方法可以重复的调用；但响应被提交后调用该方法不会产生任何作用。如果在页面被提交之前 servlet 没有设置locale，容器的默认 locale 将用来确定响应的locale，但是没有制定与客户端通信的规范，例如使用 HTTP 情况下的Content-Language 头。

```
<locale-encoding-mapping-list> <locale-encoding-mapping>
        <locale>ja</locale>
<encoding>Shift_JIS</encoding> </locale-encoding-mapping>
</locale-encoding-mapping-list>
```
&lt;response-character-encoding&gt;元素可用于为给定Web应用程序中的所有响应显式设置默认编码。

```
<response-character-encoding>UTF-8</response-character-encoding>
```

如果该元素不存在或没有提供映射，setLocale 使用容器依赖的映射。setCharacterEncoding，setContentType 和 setLocale 方法可以被重复的调用来改变字符编码。如果在 servlet 响应的 getWriter 方法已经调用之后或响应被提交之后，调用相关方法设置字符编码将没有任何作用。只有当给定的上下文类型字符串提供了一个 charset 属性值，调用 setContentType 可以设置字符编码。只有当既没有调用 setCharacterEncoding 也没有调用 setContentType 去设置字符编码之前调用 setLocale 才可以设置字符编码。
在 ServletResponse 接口的 getWriter 方法被调用或响应被提交之前，如果 servlet 没有指定字符编码，默认使用 ISO-8859-1。

如果使用的协议提供了一种这样做的方式，容器必须传递 servlet 响应的writer 使用的 locale 和字符编码到客户端。使用 HTTP 的情况下，locale 可以使用 Content-Language 头传递，字符编码可以作为用于指定文本媒体类型的 Content-Type 头的一部分传递。注意，如果没有指定上下文类型，字符编码不能通过 HTTP 头传递；但是仍使用它来编码通过servlet 响应的 writer 写的文本。

## 结束响应对象

当响应被关闭时，容器必须立即刷出响应缓冲区中的所有剩余的内容到客户端。以下事件表明 servlet 满足了请求且响应对象即将关闭：

- servlet 的 service 方法终止。
响应的 setContentLength 或 - setContentLengthLong 方法指定了大于零的内容量，且已经写入到响应。
- sendError 方法已调用。
- sendRedirect 方法已调用。
- AsyncContext 的 complete 方法已调用

## 响应对象的生命周期

每个响应对象是只有当在 servlet 的 service 方法的范围内或在 filter 的 doFilter 方法范围内是有效的，除非该组件关联的请求对象已经开启异步处理。如果相关的请求已经启动异步处理，那么直到AsyncContext 的 complete 方法被调用，请求对象一直有效。为了避免响应对象创建的性能开销，容器通常回收响应对象。在相关的请求的startAsync 还没有调用时，开发人员必须意识到保持到响应对象引用，超出之上描述的范围可能导致不确定的行为。

# Filtering (过滤器)

# Session(会话)

# <a id='Annotations'>Annotations and pluggability（注释与可插拔）</a>

# Dispatching Requests（请求分发)

# Web Applications(Web 应用)

# Application Lifecycle Events(应用生命周期事件)

# Mapping Requests to Servlets(Servlet 与访问请求的映射)

# Security(安全)

# <a id="Deployment">Deployment Descriptor(部署描述)</a>

# Requirements related to other Specifications（与其它规范关联性）

# Change Log (变更记录）

J

本文档是在Java Community ProcessSM（JCP）下开发的Java Servlet 4.0 Servlet规范的最终版本。


