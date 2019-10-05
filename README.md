## log-monitor 日志监控与Tomcat控制[![Build Status](https://travis-ci.org/apache/rocketmq.svg?branch=master)]() [![Coverage Status](https://coveralls.io/repos/github/apache/rocketmq/badge.svg?branch=master)]()
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.apache.rocketmq/rocketmq-all/badge.svg)]()
[![GitHub release](https://img.shields.io/badge/release-download-orange.svg)]()
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)]()

2019/7/25 18:00:00
----------

**编码风格**

1. 阅读阿里巴巴Java开发手册：
[https://github.com/alibaba/p3c/blob/master/阿里巴巴Java开发手册（详尽版）.pdf](https://github.com/alibaba/p3c/blob/master/阿里巴巴Java开发手册（详尽版）.pdf "阿里巴巴Java开发手册")

2. IDEA安装 Alibaba Java Coding Guidelines plugin

----------

**Git版本控制**



- **更新(pull)/提交(commit)代码**

	1. **拉取(pull)代码用rebase**,避免不必要的Merge.

	2. 不要提交编译运行时的中间产品,如.class文件.

	3. 不要提交与环境密切相关的工程配置文件,如**.iml**文件以及各种IDE自动生成的xml格式文件.

	4. **频繁commit,但在本地测试通过没有问题再push**.

	5. **每个commit必须有清晰简洁的注释**


----------


- **GitLab Clone 项目 + 分支（develop）工作流程**

	我们这里以zlapp为例，我们进入如下地址看到我们所有的项目：
	[https://github.com/thenx-projects/log-monitor.git]
	
	1. 执行如下语句直接clone + 切换分支：
	
	`$ git clone -b develop https://github.com/thenx-projects/log-monitor.git`

	2. 增加主分支的最新代码可以用下面的命令切换:

		`$ git remote add upstream https://github.com/thenx-projects/log-monitor.git`

----------

- **push上传代码**
	
	1. 每次push前执行命令:

		`$ git pull --rebase upstream develop`

	2. 如果提示出现与
`error: Your local changes to the following files would be overwritten by merge:`
`	scripts/src/Strange.java`
`Please commit your changes or stash them before you merge.`

		类似的错误,先commit必要的文件,不想commit的文件用**git stash**命令将改动暂存起来,push命令成功后再用**git stash pop取出暂存**的改动

	3. 强制同步

	出现无法解决的push/merge冲突时执行下面两条命令可以强制将本地和fork仓库与上游仓库进行同步

	`$ git reset --hard upstream/master`

	`$ git push origin master --force`

	**注意: 这样做将会丢失本地和fork仓库所有未merge的更改,万不得已的情况下请确保如果有重要文件备份后再执行强制同步操作!!!**
	
	

- **Merge Request(MR/Code Review)**
	
	1. 登录Gitlab,找到自己分支的项目
	
	2. 点击左侧Merge Requests菜单,点击绿色New merge request按钮
	
	3. 在Source branch一侧的Select source branch下拉菜单中选择master
	
	4. 点击绿色Compare branches and continue按钮
	
	5. 填写标题和必要的注释。**但是由于我们现在没有Jenkins做自动化构建，所以命名暂无规范**
	
	6. 选择Assignee
	
	7. Assignee对MR做代码审查,进行必要的交流,最后决定merge或者close
	
	8. 对于close的MR,Assignee给出理由,指出需要修改后再次提交MR的地方
	
	9. 通过MR的时候Assignee要检查自动集成是否成功,如果有失败的要让造成失败的MR提交人跟进解决
	