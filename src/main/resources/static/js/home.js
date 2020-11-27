function account() {
    $.ajax({
        type: 'GET',
        url: '/api/guest/getUser',
        dataType: "json",
        success: function (data) {
            $('#authorization-link').remove();

            let topPanel = $('#top-panel');

            topPanel.append(
                $('<a id="authorization-link" href="/getUserOrAdminPage">')
                    .append($('<div id="authorization-div" class="authorization-div">').append(
                        '<span id="authorization-text" class="authorization-text">' + data.firstName + '</span>' +
                        '<img id="authorization" src="/images/authorized.png">'
                        )
                    )
            );
        }
    })

    let ul = $('#ulNews');

    $.ajax({
        type: 'GET',
        url: '/api/guest/getNews',
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                ul.append($('<li class="news-window">').append(
                    $('<img class="news-img" src="' + data[i].imgPath + '">'),
                    $('<span class="header-text">' + data[i].headerText + '</span>'),
                    $('<span class="news-text">' + data[i].text + '</span>'),
                    $('<button id="button-like' + data[i].id + '" onclick="like(' + data[i] + ')" class="button-like">' + data[i].like + '</button>')));
            }
        }
    })
}