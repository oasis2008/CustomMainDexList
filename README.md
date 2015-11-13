# CustomMainDexList

使用Proguard规则生成自定义MainDexList

##关键代码
生成mainDexList脚本
```
task generateMainDexList(type: Exec) {
    workingDir '.'

    //on windows:
    commandLine 'cmd', '/c', 'gen-main-dex.bat'

    //on linux
//    commandLine './gen-main-dex'
    standardOutput = new ByteArrayOutputStream()
    ext.output = {
        return standardOutput.toString()
    }
}
```
设置dex前置任务
```
afterEvaluate {
    tasks.matching {
        it.name.startsWith('dex')
    }.each { dx ->
        if (dx.additionalParameters == null) {
            dx.additionalParameters = []
        }
        // optional
        dx.additionalParameters += '--multi-dex'
        dx.additionalParameters += "--main-dex-list=$projectDir/maindex.keep".toString() // enable the main-dex-list
        dx.additionalParameters += "--minimal-main-dex".toString() // enable the main-dex-list
    }
    dexDebug.dependsOn generateMainDexList
    dexRelease.dependsOn generateMainDexList
}
```
Command Line
./app/gen-main-dex.bat
```
etc\mainDexClasses.bat --output maindex.keep .\build\intermediates\multi-dex\debug\allclasses.jar
```
Proguard Rule in File<br/>
/app/etc/mainDexClasses.rules
[Link](https://github.com/eyyoung/CustomMainDexList/blob/master/app/etc/mainDexClasses.rules)
