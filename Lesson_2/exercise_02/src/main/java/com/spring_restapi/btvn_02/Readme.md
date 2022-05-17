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
### get1:

