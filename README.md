# AntiAccessibilityService

通过系统层hook等操作，拦截模拟点击功能

模拟点击是指通过脚本、系统指令完成一些自动化点击的操作，不需要人为点击，一般应用在自动化测试，帮助视障或是老年群体更方便使用手机。但随着技术的更新，模拟点击也被应用在了不同的场景，如自动化抢红包、自动聊天等黑灰产场景中。
因为 View 较为复杂，对于 View 较多的软件，如 IM、电商、短视频等软件，有时会面临抢红包、聊天 Bot、抢商品秒杀等自动化脚本的风险。
所以这类脚本往往是依赖于无障碍服务来实现的。因为系统层的支持，导致无障碍服务对 View 操作十分便利。
对于不依赖无障碍的模拟点击工具而言，想要读取 View 是很困难的，例如想要知道一个用户名，需要截图 OCR 匹配字库，所以对于这部分内容，无障碍服务具有天然的优势。

![Image text](https://raw.githubusercontent.com/Mrack/AntiAccessibilityService/master/%E5%8A%A8%E7%94%BB.gif)


![Image text](https://raw.githubusercontent.com/Mrack/AntiAccessibilityService/master/QQ%E6%88%AA%E5%9B%BE20221014185731.png)






