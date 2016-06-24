# ssh
	shiro是apache推出的安全处理策略用户解决系统中身份验证及授权的工作，但shiro不提供身份信息及权限信息的维护，都必须由系统自己提供，shiro只是对其进行验证操作。及用户信息、角色、权限需要向shiro提供。
	核心对象：1、Subject：表示一个用户对象，与系统交互的直接对象都可视为Subject
		   2、SecurityManager：安全管理器(可视为中间件)
		   3、Realm：安全信息数据源(提供身份、权限、角色等信息)
	1. shiro和spring整合
		1、引入jar包：通过maven构建
		2、在web.xml文件中配置-->该过滤器需要在spring中进行声明
			`<filter>
			    <filter-name>shiroFilter</filter-name>
			    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
			</filter>
			<filter-mapping>
			    <filter-name>shiroFilter</filter-name>
			    <url-pattern>/*</url-pattern>
			</filter-mapping>`
		3、在spring-shiro配置文件中配置如下信息：
			1、配置凭证匹配器：用于在身份验证的时候匹配密码是否一致
			2、Realm信息(继承AuthorizingRealm)：用户提供身份验证和权限验证，注入凭证匹配器
			3、配置securityManage注入Realm
			4、配置基于Form表单的身份验证过滤器：用户处理用户登录身份验证
			5、配置 Shiro Filter：注入securityManager，以及权限过滤器(不同的url采用不同的过滤器)
			6、如果使用注解的方式处理权限认证--->针对方法上的(主要采用aop实现)
				`
				<!-- Shiro生命周期处理器-->
			    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>
			
				<!-- 注解配置 -->
				<aop:config proxy-target-class="true"></aop:config>
				<bean
					class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
					<property name="securityManager" ref="securityManager" />
				</bean>
				`
	2、身份验证流程：
		1、采用基于表单的身份验证策略
			1、调用：AuthenticatingRealm.getAuthenticationInfo(AuthenticationToken)-->AuthenticationToken为UsernamePasswordToken的实例
			2、该方法先获取缓存的身份信息如果没有则调用doGetAuthenticationInfo(抽象方法,子类中)进行身份验证
			3、调用assertCredentialsMatch方法进行凭证的匹配
				该方法中先获取凭证匹配器再调用其CredentialsMatcher.doCredentialsMatch(AuthenticationToken, AuthenticationInfo)进行凭证匹配
			4、凭证匹配成功及标识为身份验证成功：则跳转到Shiro Filter所配置的successUrl界面
	3、授权流程：
		1、调用：AuthorizingRealm.getAuthorizationInfo(PrincipalCollection)-->用户名
		2、该方法中也先从缓存中获取如果没有则调用AuthorizingRealm.doGetAuthorizationInfo(PrincipalCollection)抽象方法
		3、如果没有权限则跳转到Shiro Filter所配置的unauthorizedUrl界面
		[This link](http://www.tuicool.com/articles/AFFBre)
