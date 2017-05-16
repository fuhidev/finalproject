<<<<<<< HEAD
# I. Luận văn tốt nghiệp
## 1. Tìm hiểu đề tài
__Tên đề tài__
> Chương trình hỗ trợ chọn lựa phương án báo vệ tài sản

__Mục tiêu__
> Xây dựng được ứng dụng đo giá vàng và dollar trong hiện tại và cung cấp giải pháp đầu tư trong tương lai thông qua các dữ liệu phân tích được.
## 2. Thu thập dữ liệu
2.1 Các bước thu thập dữ liệu
* Tìm kiếm website cho phép thu thập các dữ liệu giá vàng, dollar trong 10 năm bắt đầu từ hiện tại trở về trước, và dữ liệu này phải được cập nhật liên tục mỗi ngày.
* Sử dụng các API được hỗ trợ để crawl dữ liệu từ website về để thu thập được dữ liệu mong muốn.
* Lưu trữ dữ liệu trong dababase để tái sử dụng.

2.2 Các khó khăn trong quá trình thu thập dữ liệu
* Luồng dữ liệu không đảm bảo thông suốt( nghĩa là vẫn tồn tại những ngày không có dữ liệu truyền về).
* Dữ liệu thu thập được lớn ban đầu gây khó khăn trong việc xử lý mảng lưu trữ.
## 3. Xây dựng biểu đồ
=======
# Web-Services

This is a branch use PHP to write services

Services use php-cruid-api GNU.

# Features
## Get Json Data
```url
  localhost/services.php/{table}/{id}
```
# Example:

## Get list jsondata with table usgold

```
localhost/services.php/usgold
```
### Return
```json
{
	"usgold": {
		"columns": ["id", "name", "vnprice", "usprice", "datetime"],
		"records": [
			[1, "us", 1842229.15654, 408.6, "04\/12\/1989"],
			[2, "us", 1812249.2624, 403.75, "05\/12\/1989"],
			[3, "us", 1801898.71943, 402.85, "06\/12\/1989"],
			[4, "us", 1817140.22006, 406, "07\/12\/1989"],
			[5, "us", 1826915.21182, 409.35, "08\/12\/1989"],
			[6, "us", 1862927.68799, 415.5, "11\/12\/1989"],
			[7, "us", 1846662.70577, 415.5, "12\/12\/1989"],
			[8, "us", 1845333.67921, 413, "13\/12\/1989"],
			[...]
		]
	}
}
				
```

## Get a jsondata with table usgold and id

```
localhost/services.php/usgold/1
```
### Return

```json
{
	"id": 1,
	"name": "us",
	"vnprice": 1842229.15654,
	"usprice": 408.6,
	"datetime": "04\/12\/1989"
}
```
>>>>>>> a5f052b6ed1e3faf87b3f9344ab9d4c60647647f
