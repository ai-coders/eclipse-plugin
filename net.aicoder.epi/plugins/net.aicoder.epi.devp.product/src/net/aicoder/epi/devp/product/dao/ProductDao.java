package net.aicoder.epi.devp.product.dao;

import java.util.ArrayList;
import java.util.List;

import net.aicoder.epi.base.dao.BaseDao;
import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.devp.DevpConstant;
import net.aicoder.epi.devp.product.model.product.PrdDiagramCatgVo;
import net.aicoder.epi.devp.product.model.product.PrdDiagramVo;
import net.aicoder.epi.devp.product.model.product.PrdPrdlineVo;
import net.aicoder.epi.devp.product.model.product.PrdProductVo;

public class ProductDao  extends BaseDao{
	private long currUid = 1L;
	
	public ProductDao() {
		super();
	}
	
	public List<IBaseVo> listProduct(){
		long uid = currUid;
		List<IBaseVo> list = null;
		list = _listProductByUid(uid);
		return list;
	}

	private List<IBaseVo> _listProductByUid(long uid) {
		List<IBaseVo> list = new ArrayList<IBaseVo>(0);
		PrdPrdlineVo prdLine1 = new PrdPrdlineVo();
		prdLine1.setRid(100000);
		prdLine1.setCode("PL_C100000");
		prdLine1.setName("PL_N100000");
		list.add(prdLine1);

		PrdPrdlineVo prdLine11 = new PrdPrdlineVo();
		prdLine11.setRid(110000);
		prdLine11.setCode("PL_C110000");
		prdLine11.setName("PL_N110000");
		prdLine11.setParentPrdline(prdLine1);
		prdLine1.getChildrenPrdlineList().add(prdLine11);

		PrdProductVo product1 = new PrdProductVo();
		product1.setRid(1);
		product1.setCode("PD_C1");
		product1.setName("PD_N1");
		product1.setParentPrdline(prdLine1);
		prdLine1.getProductList().add(product1);

		PrdDiagramCatgVo prdCpnt11 = new PrdDiagramCatgVo();
		prdCpnt11.setCode("PrdCpnt");
		prdCpnt11.setName("Component Diagrams");
		prdCpnt11.setType(DevpConstant.E_PRD_CPNT);
		prdCpnt11.setParentProduct(product1);
		product1.getChildrenList().add(prdCpnt11);

		PrdDiagramVo prdCpnt111 = new PrdDiagramVo();
		prdCpnt111.setRid(111);
		prdCpnt111.setCode("PrdCpnt");
		prdCpnt111.setName("系统组件视图");
		prdCpnt111.setType(DevpConstant.E_PRD_CPNT);
		prdCpnt111.setParentDiagramCatg(prdCpnt11);
		prdCpnt11.getChildrenList().add(prdCpnt111);

		PrdDiagramCatgVo prdIdeprj12 = new PrdDiagramCatgVo();
		prdIdeprj12.setCode("PrdCpnt");
		prdIdeprj12.setName("DevProject Diagrams");
		prdIdeprj12.setType(DevpConstant.E_PRD_IDEPRJ);
		prdIdeprj12.setParentProduct(product1);
		product1.getChildrenList().add(prdIdeprj12);

		PrdDiagramVo prdIdeprj121 = new PrdDiagramVo();
		prdIdeprj121.setRid(121);
		prdIdeprj121.setCode("PrdIdeprj");
		prdIdeprj121.setName("Prj_Front");
		prdIdeprj121.setType(DevpConstant.E_PRD_IDEPRJ);
		prdIdeprj121.setParentDiagramCatg(prdIdeprj12);
		prdIdeprj12.getChildrenList().add(prdIdeprj121);

		PrdDiagramVo prdIdeprj122 = new PrdDiagramVo();
		prdIdeprj122.setRid(122);
		prdIdeprj122.setCode("PrdIdeprj");
		prdIdeprj122.setName("Prj_Backend");
		prdIdeprj122.setType(DevpConstant.E_PRD_IDEPRJ);
		prdIdeprj122.setParentDiagramCatg(prdIdeprj12);
		prdIdeprj12.getChildrenList().add(prdIdeprj122);

		PrdDiagramCatgVo prdDeploy13 = new PrdDiagramCatgVo();
		prdDeploy13.setCode("PrdDeploy");
		prdDeploy13.setName("Deployment Diagrams");
		prdDeploy13.setType(DevpConstant.E_PRD_DEPLOY);
		prdDeploy13.setParentProduct(product1);
		product1.getChildrenList().add(prdDeploy13);

		PrdDiagramVo prdDeploy131 = new PrdDiagramVo();
		prdDeploy131.setRid(131);
		prdDeploy131.setCode("PrdDeploy");
		prdDeploy131.setName("部署视图[测试]");
		prdDeploy131.setType(DevpConstant.E_PRD_DEPLOY);
		prdDeploy131.setParentDiagramCatg(prdDeploy13);
		prdDeploy13.getChildrenList().add(prdDeploy131);

		PrdDiagramVo prdDeploy132 = new PrdDiagramVo();
		prdDeploy132.setRid(132);
		prdDeploy132.setCode("PrdDeploy");
		prdDeploy132.setName("部署视图[验证]");
		prdDeploy132.setType(DevpConstant.E_PRD_DEPLOY);
		prdDeploy132.setParentDiagramCatg(prdDeploy13);
		prdDeploy13.getChildrenList().add(prdDeploy132);

		PrdDiagramVo prdDeploy133 = new PrdDiagramVo();
		prdDeploy133.setRid(133);
		prdDeploy133.setCode("PrdDeploy");
		prdDeploy133.setName("部署视图[生产]");
		prdDeploy133.setType(DevpConstant.E_PRD_DEPLOY);
		prdDeploy133.setParentDiagramCatg(prdDeploy13);
		prdDeploy13.getChildrenList().add(prdDeploy133);

		PrdProductVo product2 = new PrdProductVo();
		product2.setRid(1);
		product2.setCode("PD_C2");
		product2.setName("PD_N2");
		list.add(product2);

		PrdDiagramCatgVo prdCpnt21 = new PrdDiagramCatgVo();
		prdCpnt21.setCode("PrdCpnt");
		prdCpnt21.setName("Component Diagrams");
		prdCpnt21.setType(DevpConstant.E_PRD_CPNT);
		prdCpnt21.setParentProduct(product2);
		product2.getChildrenList().add(prdCpnt21);

		PrdDiagramCatgVo prdIdeprj22 = new PrdDiagramCatgVo();
		prdIdeprj22.setCode("PrdCpnt");
		prdIdeprj22.setName("DevProject Diagrams");
		prdIdeprj22.setType(DevpConstant.E_PRD_IDEPRJ);
		prdIdeprj22.setParentProduct(product2);
		product2.getChildrenList().add(prdIdeprj22);

		PrdDiagramCatgVo prdDeploy23 = new PrdDiagramCatgVo();
		prdDeploy23.setCode("PrdDeploy");
		prdDeploy23.setName("Deployment Diagrams");
		prdDeploy23.setType(DevpConstant.E_PRD_DEPLOY);
		prdDeploy23.setParentProduct(product2);
		product2.getChildrenList().add(prdDeploy23);

		return list;
	}
}
