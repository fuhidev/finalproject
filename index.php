<?php
/**
 * Created by PhpStorm.
 * User: fuhi
 * Date: 06/05/2017
 * Time: 11:21
 */


if(isset($_GET["usdgold"])){
    // kiểm tra định dạng dữ liểu trả ra là json hay xml
    $format = 'xml';
    if(isset($_GET['format']))
        $format = strtolower($_GET['format']) == 'json' ? 'json' : 'xml';


    //tạo mạng users để lưu thông tin toàn bộ user trong db
    $users = array();

    //gọi file kết nối db
    require_once(dirname(__FILE__). "/includes/connection.php");
    //truy vấn lấy toàn bộ thông tin trong bảng users
    $sql = "select * from usgold";
    $query = mysqli_query($conn,$sql);
    while ($user = mysqli_fetch_assoc($query)) {
        $users[] = array('usgold'=>$user);

    }
    // trả ra dữ liệu dưới dạng json
    if ($format == 'json') {
        header('Content-type: application/json');
        echo json_encode(array('usgolds'=>$users));
    }else{
        // trả ra dữ liệu dưới dạng xml
        header('Content-type: text/xml');
        echo '<usgolds>';
        foreach($users as $index => $user) {
            if(is_array($user)) {
                foreach($user as $key => $value) {
                    echo '<',$key,'>';
                    if(is_array($value)) {
                        foreach($value as $tag => $val) {
                            echo '<',$tag,'>',htmlentities($val),'</',$tag,'>';
                        }
                    }
                    echo '</',$key,'>';
                }
            }
        }
        echo '</usgolds>';
    }
    mysqli_close($conn);
}else{
    echo "Không có dữ liệu trả về";
}