function config(editor) {
    editor.customConfig.onchange = function (html) {
        // html 即变化之后的内容
        console.log(html)
    }
    // 手动关闭掉粘贴样式的过滤
    editor.customConfig.pasteFilterStyle = false;

    editor.customConfig.zIndex = 0;

    editor.customConfig.linkImgCallback = function (url) {
        console.log(url) // url 即插入图片的地址
    }
    // 插入链接时，可通过如下配置对文字和链接进行校验。v3.0.10开始支持。
    editor.customConfig.linkCheck = function (text, link) {
        console.log(text) // 插入的文字
        console.log(link) // 插入的链接

        return true // 返回 true 表示校验成功
        // return '验证失败' // 返回字符串，即校验失败的提示信息
    }
    // 插入网络图片时，可对图片地址做自定义校验。v3.0.13开始支持。
    editor.customConfig.linkImgCheck = function (src) {
        console.log(src) // 图片的链接

        return true // 返回 true 表示校验成功
        // return '验证失败' // 返回字符串，即校验失败的提示信息
    }

    // 自定义配置颜色（字体颜色、背景色）
    editor.customConfig.colors = [
        '#000000',
        '#eeece0',
        '#1c487f',
        '#4d80bf',
        '#c24f4a',
        '#8baa4a',
        '#7b5ba1',
        '#46acc8',
        '#f9963b',
        '#ffffff'
    ];
    // 配置表情，支持图片格式和 emoji

    // 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
    editor.customConfig.emotions = [
        {
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: [
                {
                    alt: '[坏笑]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
                },
                {
                    alt: '[舔屏]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
                }
            ]
        },
        {
            // tab 的标题
            title: 'emoji',
            // type -> 'emoji' / 'image'
            type: 'emoji',
            // content -> 数组
            content: ['😀', '😃', '😄', '😁', '😆']
        }
    ];

    // 自定义字体
    editor.customConfig.fontNames = [
        '宋体',
        '微软雅黑',
        'Arial',
        'Tahoma',
        'Verdana'
    ];

    editor.customConfig.uploadImgServer = '/upload';

    // 默认限制图片大小是 5M
    editor.customConfig.uploadImgMaxSize = 10 * 1024 * 1024;

    // 如果还需要将参数拼接到 url 中，可再加上如下配置
    editor.customConfig.uploadImgParamsWithUrl = true;

    editor.customConfig.uploadFileName = 'file';

    // 上传图片的错误提示默认使用alert弹出
    editor.customConfig.customAlert = function (info) {
        // info 是需要提示的内容
        alert('自定义提示：' + info)
    }

    editor.customConfig.onblur = function (html) {
        // html 即编辑器中的内容
        console.log('onblur', html)
    }
}