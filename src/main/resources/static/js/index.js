function api() {
    // Handle logout link click
    //$("#api-link").click(function(event) {
        event.preventDefault();
        Swal.fire({
            title: 'Are you sure?',
            text: "Are you sure you want to go to the API page? Current page and embedded API page! And the entry page cannot jump back normally!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, let me go!'
        }).then((result) => {
            if (result.isConfirmed) {
                $.get("/api", function(data) {
                    // window.location.href = "/doc.html#/home";
                    window.location.href = "/api";
                });
            }
        });
    //});

    // Handle learn more button click
    $("#learn-more-button").click(function(event) {
        event.preventDefault();
        // Do something here
    });
}


function logout() {
    //$("#logout-link").click(function(event) {
        event.preventDefault();
        Swal.fire({
            title: 'Are you sure?',
            text: "You will be logged out and redirected to the login page!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, log me out!'
        }).then((result) => {
            if (result.isConfirmed) {
                $.post("/logout", function(data) {
                    window.location.href = "/login";
                });
            }
        });
    //});

    // Handle learn more button click
    $("#learn-more-button").click(function(event) {
        event.preventDefault();
        // Do something here
    });
}

// function fullscreen(){
// event.preventDefault();
// // 当 iframe 加载完毕后，调整它的高度
// var iframe = document.getElementById('my-iframe');
// iframe.addEventListener('load', function() {
//     var doc = iframe.contentWindow.document;
//     var iframeHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight);
//     iframe.style.height = iframeHeight + 'px';
// });
//
// var fullscreenButton = document.getElementById('fullscreen-button');
//
// // 检测浏览器是否支持 Fullscreen API
// if (document.fullscreenEnabled) {
//     // 添加点击事件监听器
//     fullscreenButton.addEventListener('click', function() {
//         // 获取要全屏显示的元素
//         var element = document.documentElement;
//
//         // 如果当前不在全屏状态，那么进入全屏模式
//         if (!document.fullscreenElement) {
//             element.requestFullscreen();
//         } else {
//             // 如果当前在全屏状态，那么退出全屏模式
//             document.exitFullscreen();
//         }
//     });
// } else {
//     // 如果不支持 Fullscreen API，禁用按钮
//     fullscreenButton.disabled = true;
// }
// }

function fullscreen() {
    var iframe = document.getElementById('my-iframe');
    var fullscreenButton = document.getElementById('fullscreen-button');

    // 添加iframe加载完成事件监听器
    iframe.addEventListener('load', function() {
        var doc = iframe.contentWindow.document;
        var iframeHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight);
        iframe.style.height = iframeHeight + 'px';
    });

    // 检测浏览器是否支持Fullscreen API
    if (document.fullscreenEnabled) {
        // 添加点击事件监听器
        fullscreenButton.addEventListener('click', function() {
            // 获取要全屏显示的元素
            var element = document.documentElement;

            // 如果当前不在全屏状态，那么进入全屏模式
            if (!document.fullscreenElement) {
                element.requestFullscreen();
            } else {
                // 如果当前在全屏状态，那么退出全屏模式
                document.exitFullscreen();
            }
        });
    } else {
        // 如果不支持Fullscreen API，禁用按钮
        fullscreenButton.disabled = true;
    }
}