# h2
http://www.h2database.com/

#### [官网](http://www.h2database.com/html/main.html)
- <http://www.h2database.com/html/cheatSheet.html>
- <http://www.h2database.com/html/quickstart.html>
- <http://www.h2database.com/html/installation.html>
- <http://www.h2database.com/html/tutorial.html>
- <http://www.h2database.com/html/performance.html>
- <http://www.h2database.com/html/advanced.html>
- <http://www.h2database.com/html/grammar.html>
- <http://www.h2database.com/html/functions.html>
- <http://www.h2database.com/html/datatypes.html>

#### Features
|  | H2 | Derby | HSQLDB | MySQL | PostgreSQL |
| --- | --- | --- | --- | --- | --- |
| Pure Java |	Yes |	Yes |	Yes | No | No |
| Memory Mode | Yes | Yes | Yes | No | No |
| Encrypted Database | Yes | Yes | Yes | No | No |
| ODBC Driver | Yes | No | No | Yes | Yes |
| Fulltext Search | Yes | No | No | Yes | Yes |
| Multi Version Concurrency | Yes | No | Yes | Yes | Yes |
| Footprint (jar/dll size) | ~1 MB | ~2 MB | ~1 MB | ~4 MB | ~6 MB |

#### 控制台
通过双击`bin/h2-xxx.jar`或`bin/h2.bat`启动控制台程序。启动后访问`http://127.0.0.1:8082/login.jsp`进入控制台(WEB界面)。

控制台界面上提供了`Preferences`和`Tools`，可以用来配置控制台和提供一些工具，如：备份等

控制台允许设置语言，这里使用`简体中文`

连接设置可以下拉选择多种模式，这里选择`Embedded`模式，URL值为`jdbc:h2:mem:test`，表示运行于内存中，点击确定后，进入管理面板。

#### 连接方式
- In-Memory
```
jdbc:h2:mem:test multiple connections in one process
jdbc:h2:mem: unnamed private; one connection
```
- Embedded
```
jdbc:h2:~/test 'test' in the user home directory
jdbc:h2:/data/test 'test' in the directory /data
jdbc:h2:test in the current(!) working directory
```
- Server Mode
```
jdbc:h2:tcp://localhost/~/test user home dir
jdbc:h2:tcp://localhost//data/test absolute dir
Server start:java -cp *.jar org.h2.tools.Server
```
- Settings
```
jdbc:h2:..;MODE=MySQL compatibility (or HSQLDB,...)
jdbc:h2:..;TRACE_LEVEL_FILE=3 log to *.trace.db
```
