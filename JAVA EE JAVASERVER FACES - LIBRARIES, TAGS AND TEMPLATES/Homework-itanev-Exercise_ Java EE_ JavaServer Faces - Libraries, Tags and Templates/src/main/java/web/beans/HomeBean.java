package web.beans;

import fdmc.domain.models.binding.CatCreateBindingModel;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Id;
import java.io.IOException;
import java.util.List;

@Named
@RequestScoped
public class HomeBean {

    private CatCreateBindingModel catCreateBindingModel;

    private CatService catService;
    private ModelMapper modelMapper;

    public HomeBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public HomeBean(CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void create() throws IOException {


        this.catService.createCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/index.xhtml");
    }

//    private void initPackages() {
//        CatServiceModel catServiceModel = catService
//                .findUserByUsername((String) ((HttpSession) FacesContext
//                        .getCurrentInstance()
//                        .getExternalContext()
//                        .getSession(true)).getAttribute("username"));
//
//        this.pendingPackages = userServiceModel
//                .getPackages()
//                .stream()
//                .filter(p -> p.getStatus().name().equals("Pending"))
//                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
//                .collect(Collectors.toList());
//
//        this.shippedPackages = userServiceModel
//                .getPackages()
//                .stream()
//                .filter(p -> p.getStatus().name().equals("Shipped"))
//                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
//                .collect(Collectors.toList());
//
//        this.deliveredPackages = userServiceModel
//                .getPackages()
//                .stream()
//                .filter(p -> p.getStatus().name().equals("Delivered"))
//                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
//                .collect(Collectors.toList());
//    }

}
