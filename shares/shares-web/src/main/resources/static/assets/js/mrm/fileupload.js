function upload(event, callback) {
    var strUploadErr= '上传失败';
    var _this = this;
    var _fileInput = event.target;
    var _files = event.target.files;
    if (_files.length == 0) return false;
    if (_files[0].size >= 1048576) {
        layer.alert('图片尺寸超过1M');
        event.target.value = '';
        return false;
    }

    $.ajax({
        type: 'post',
        url: '/seaweedfs/getSign',
        success: function success(data) {
            if (data.error_code == 0 && data.data) {
                uploadImage(data.data, _files, _fileInput);
            } else {
                layer.alert(strUploadErr, {closeBtn: false});
            }
        },
        error: function error(data) {
            layer.alert(strUploadErr, {closeBtn: false});
        }
    });

    function uploadImage(_sign, _files, _fileInput) {
        _this.fileuploading = true;
        var fdata = new FormData();
        fdata.append('file', _files[0]);
        $.ajax({
            url: _sign.uploadUrl + '/submit',
            type: 'POST',
            data: fdata,
            contentType: false,
            processData: false,
            beforeSend: function beforeSend(request) {
                request.setRequestHeader('Content-MD5', _sign['Content-MD5']);
                request.setRequestHeader('DateTime', _sign['DateTime']);
                request.setRequestHeader('Authorization', _sign['Authorization']);
            },
            success: function success(data) {
                data = JSON.parse(data);
                if (data.fid) {
                    callback && callback({
                        url: _sign.downloadUrl + '/' + data.fid,
                        uploadimgid: data.fid
                    });
                } else {
                    layer.alert(strUploadErr, {closeBtn: false});
                }
            },
            error: function error(XMLHttpRequest, textStatus, errorThrown) {
                layer.alert(strUploadErr, {closeBtn: false});
            },
            complete: function complete() {
                _this.fileuploading = false;
            }
        });
    }
}