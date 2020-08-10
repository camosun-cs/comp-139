/*
 * Copyright (c) 2013, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
var moduleSearchIndex;
var packageSearchIndex;
var typeSearchIndex;
var memberSearchIndex;
var tagSearchIndex;
function createElem(doc, tag, path) {
    var script = doc.createElement(tag);
    var scriptElement = doc.getElementsByTagName(tag)[0];
    script.src = pathtoroot + path;
    scriptElement.parentNode.insertBefore(script, scriptElement);
}

function show(type) {
    count = 0;
    for (var key in data) {
        var row = document.getElementById(key);
        if ((data[key] &  type) !== 0) {
            row.style.display = '';
            row.className = (count++ % 2) ? rowColor : altColor;
        }
        else
            row.style.display = 'none';
    }
    updateTabs(type);
}

function updateTabs(type) {
    var firstRow = document.getElementById(Object.keys(data)[0]);
    var table = firstRow.closest('table');
    for (var value in tabs) {
        var tab = document.getElementById(tabs[value][0]);
        if (value == type) {
            tab.className = activeTableTab;
            tab.innerHTML = tabs[value][1];
            tab.setAttribute('aria-selected', true);
            tab.setAttribute('tabindex',0);
            table.setAttribute('aria-labelledby', tabs[value][0]);
        }
        else {
            tab.className = tableTab;
            tab.setAttribute('aria-selected', false);
            tab.setAttribute('tabindex',-1);
            tab.setAttribute('onclick', "show("+ value + ")");
            tab.innerHTML = tabs[value][1];
        }
    }
}

function switchTab(e) {
    var t;
    if (e.keyCode == 37 || e.keyCode == 38) {
        t = document.querySelector("[aria-selected=true]").previousElementSibling;
        e.preventDefault();
    }
    if (e.keyCode == 39 || e.keyCode == 40) {
        t = document.querySelector("[aria-selected=true]").nextElementSibling;
        e.preventDefault();
    }
    if (t) {
        t.click();
        t.focus();
    }
}
