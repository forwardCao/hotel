// <!--�ƶ��˰汾���� -->
var mengvalue = -1;
//if(mengvalue<0){mengvalue=0;}
var phoneWidth = parseInt(window.screen.width);
var phoneScale = phoneWidth / 750;
var ua = navigator.userAgent;
if (/Android (\d+\.\d+)/.test(ua)) {
    var version = parseFloat(RegExp.$1);
    // andriod 2.3
    if (version > 2.3) {
        document.write('<meta name="viewport" content="width=750, minimum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi">');
        // andriod 2.3����
    } else {
        document.write('<meta name="viewport" content="width=750, target-densitydpi=device-dpi">');
    }
    // ����ϵͳ
} else {
    document.write('<meta name="viewport" content="width=750, user-scalable=no, target-densitydpi=device-dpi">');
}

// rem�ߴ�
(function(doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function() {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
        };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);