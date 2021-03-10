### 代码规范

- **缩进**

  采用4个空格，禁止使用tab字符。

- **变量命名**     

  成员变量、局部变量都统一使用lowerCamelCase风格，必须遵从驼峰形式。

- **每行最多字符数**   

  单行字符数限制不超过 120个，超出需要换行，换行时遵循如下原则：

  - 第二行相对第一行缩进 4个空格，从第三行开始，不再继续缩进。
  - 运算符与下文一起换行。
  - 方法调用的点符号与下文一起换行。
  - 在多个参数超长，逗号后进行换行。
  - 在括号前不要换行。

- **函数最大行数**    

  80行   

- **函数、类命名**

  函数名使用lowerCamelCase风格，类名使用UpperCamelCase风格，都必须遵从驼峰形式，但以下情形例外：（领域模型的相关命名）DO / BO / DTO / VO等。

- **常量**    

  常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不嫌名字长。

- **空行规则**

  方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。

- **注释规则**

  - 类、类属性、类方法的注释必须使用Javadoc规范，使用/**内容*/格式，不得使用//xxx方式。

  - 所有的抽象方法（包括接口中的方法）必须要用Javadoc注释、除了返回值、参数、异常说明外，还必须指出该方法做什么事情，实现什么功能。

  - 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/* */注释，注意与代码对齐。

  - 所有的枚举类型字段必须要有注释，说明每个数据项的用途。

- **操作符前后空格**

  任何运算符左右必须加一个空格

- **其他规则**

  - 在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。

  - 大括号的使用约定

    如果是大括号内为空，则简洁地写成{}即可，不需要换行；

    如果是非空代码块则：

    - 左大括号前不换行。

    - 左大括号后换行。

    - 右大括号前换行。

    - 右大括号后还有else,catch等代码则不换行；

      表示终止右大括号后必须换行。