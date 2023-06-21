package dev.ddthanh.jobsgobe.controller.packagee;
import dev.ddthanh.jobsgobe.payload.request.packagee.PackageRequest;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.payload.response.packagee.PackageRespone;
import dev.ddthanh.jobsgobe.service.impl.packagee.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class PackageeController {
    private final PackageService packageeService;
    @GetMapping("/packages")
    public Response<List<PackageRespone>>showAll(){
        Response<List<PackageRespone>> packResponse = packageeService.showAll();
        return packResponse;
    }
    @GetMapping("package/{id}")
    public Response<PackageRespone> showOnePackage(@PathVariable Long id) {
        Response<PackageRespone> packResponse = packageeService.showOnePackage(id);
        return packResponse;
    }

    @PostMapping("/package")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<PackageRespone> create(@RequestBody PackageRequest request) {
        Response<PackageRespone> packResponse = packageeService.create(request);
        return packResponse;
    }

    @PutMapping("/package/update/{id}")
    @Secured({"RECRUITER", "ADMIN"})
    public Response<PackageRespone> update(@PathVariable Long id, @RequestBody PackageRequest request) {
        Response<PackageRespone> packResponse = packageeService.update(id, request);
        return packResponse;
    }
    @DeleteMapping("/package-delete/{id}")
    @Secured({"RECRUITER", "ADMIN"})
    public void deleteById(@PathVariable Long id){
        packageeService.delete(id);
    }
}

