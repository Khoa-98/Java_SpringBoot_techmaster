# Description my project 

## Thêm job: 
```java
@PostMapping
    public Job createJob(@RequestBody JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newJob = new Job(uuid, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(uuid, newJob);
        return newJob;
    }
```
**Kết quả**
![image](https://user-images.githubusercontent.com/95128548/168781776-8c73358d-31aa-4c48-b0f6-382803394904.png)

## Sửa job:
```java
  @PutMapping(value = "/{id}")
    public Job updateJobById(@RequestParam String id,  @RequestBody JobRequest jobRequest) {
        Job updateJob = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.replace(id, updateJob);
        return updateJob;
    }
```
**Kết quả**
![image](https://user-images.githubusercontent.com/95128548/168789205-7c294761-aa32-4387-8f04-529cdcfd1001.png)

## Xoa job:
```java
@DeleteMapping(value = "/{id}")
    public Job deleteJobById(@RequestParam String id) {
        Job removeJob = jobs.remove(id);
        return removeJob;
    }
```

**Kết quả**
![image](https://user-images.githubusercontent.com/95128548/168786717-9d5425a9-457d-4e61-811f-5bd81e860616.png)


## Get bổ sung:
### get4:  
http://localhost:8080/job/query?location={location}&keyword={keyword} tìm các job mà title hoặc description chứa {keyword} đồng thời location ={location}

```java
 @GetMapping(value = "query")
    public List<Job> getJobByquery(@RequestParam("keyword") String keyword,
            @RequestParam("location") Location location) {
        return jobs.values().stream()
                .filter(j -> (j.getTitle().toLowerCase().contains(keyword)
                        || j.getDescription().toLowerCase().contains(
                                keyword))
                        && j.getLocation().equals(location))
                .collect(Collectors.toList());
    }
```
**Kết quả** 
![image](https://user-images.githubusercontent.com/95128548/169101464-f10d3e33-d3b5-45b2-88e0-bbc035ffc5cf.png)  

### Get1:
http://localhost:8080/job/sortbylocation: sắp xếp danh sách job theo thành phố tuyển

```java
@GetMapping(value = "/sortjobbylocation")
    public List<Job> sortJobByLocation() {
        return jobs.values().stream()
                .sorted(Comparator.comparing(j -> j.getLocation().getValue()))
                .collect(Collectors.toList());
    }
```
**Kết quả**  
![image](https://user-images.githubusercontent.com/95128548/169102084-0ad39298-f817-44cd-bd8f-3df6ee77c39c.png)  

### Get2:
http://localhost:8080/job/salary/{salary}: tìm các job mà {salary} trong khoảng min_salary và max_salary
```java
  @GetMapping(value = "/salary/{salary}")
    public List<Job> getJobBysalary(@PathVariable("salary") Integer salary) {
        return jobs.values().stream()
                .filter(j -> j.getMin_salary() <= salary && j.getMax_salary() >= salary)
                .collect(Collectors.toList());
    }
```
**Kết quả**  
![image](https://user-images.githubusercontent.com/95128548/169102347-170054ef-1704-44fb-876a-186a70bc8197.png)  

### Get3:
http://localhost:8080/job/keyword/{keyword} tìm các job mà title hoặc description chứa {keyword}
```java
    @GetMapping(value = "/keyword/{keyword}")
    public List<Job> getJobByKeyword(@PathVariable("keyword") String keyword) {
        return jobs.values().stream()
                .filter(j -> j.getTitle().toLowerCase().contains(keyword)
                        || j.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
```

**Kết quả**  
![image](https://user-images.githubusercontent.com/95128548/169102706-7d625960-3c27-48d1-b81f-3b9b7174f155.png)

