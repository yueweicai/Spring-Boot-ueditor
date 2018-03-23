# Spring-Boot-ueditor 后端项目案例

#### 此案例中使用jsp作为ueditor组件的控制器，用以处理所有来自前端ueditor组件请求。
#### 为了解决跨域问题，编写了一个SpringBoot配置类，代码如下：

##### @Configuration
##### public class CorsConfig {
##### 	private CorsConfiguration buildConfig() {
#####         CorsConfiguration corsConfiguration = new CorsConfiguration();
#####         corsConfiguration.addAllowedOrigin("*"); 
#####         corsConfiguration.addAllowedHeader("*"); 
#####         corsConfiguration.addAllowedMethod("*"); 
#####         return corsConfiguration;
#####     }
##### 
#####     @Bean
#####     public CorsFilter corsFilter() {
#####         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
#####         source.registerCorsConfiguration("/**", buildConfig()); 
#####        return new CorsFilter(source);
#####     }
##### }

#### 可根据实际项目的服务器域名对上述配置加以调整。

#### 通过Maven加载ueditor所需所有jar包，在pom.xml中加入：

##### <dependency>
#####     <groupId>cn.songxinqiang</groupId>
#####     <artifactId>com.baidu.ueditor</artifactId>
#####     <version>1.1.2-offical</version>
##### </dependency>

#### 在项目webapp中加入来自于ueditor组件的 config.json 文件。

#### 修改 config.json ，将所有 ...UrlPrefix 属性值设置为服务器端项目的URL地址。
#####  "imageUrlPrefix": "http://localhost:8080/editor",
#####  "imagePathFormat": "/uploads/image/{yyyy}{mm}{dd}/{time}{rand:6}",
#####  ......
#### imageUrlPrefix的设置目的在于图片回传到前台项目中将自动在图片路径中加入服务器端域名。

#### 加入一个Servlet作为 UEditor 组件的控制器，主要代码如下：
##### @WebServlet("/ueditor")
##### public class UEditorController extends HttpServlet{
##### 	public void doPost(HttpServletRequest request,HttpServletResponse response){
##### 		String action = request.getParameter("action");
##### 		ServletContext context = request.getServletContext();
##### 		String rootPath = context.getRealPath( "/" );
##### 		String str = new ActionEnter( request, rootPath ).exec();
##### 		if( action!=null && (action.equals("listfile") || action.equals("listimage") ) ){  
##### 	        rootPath = rootPath.replace("\\", "/");  
##### 	        str = str.replaceAll(rootPath, "/");  
##### 	  }
##### 		PrintWriter out = response.getWriter();
##### 		out.write(str);
##### 		out.flush();
##### 		out.close();
##### 	}
#####   ......
##### }
