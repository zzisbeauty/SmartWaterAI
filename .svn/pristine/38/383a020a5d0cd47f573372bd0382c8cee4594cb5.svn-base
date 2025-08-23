package com.hanwei.core.base;


import com.baomidou.mybatisplus.extension.service.IService;

import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.apache.commons.beanutils.PropertyUtils;




/**
 * @Description:[基础Controller]
 */
@Slf4j
public class BaseController<T, S extends IService<T>> {
    @Autowired
    protected S service;

//    @Value("${hanwei.path.upload}")
//    private String upLoadPath;
//    /**
//     * 导出excel
//     *
//     * @param request
//     */
//    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title) {
//        // Step.1 组装查询条件
//        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
//
//        // 过滤选中数据
//        String selections = request.getParameter("selections");
//        if (oConvertUtils.isNotEmpty(selections)) {
//            List<String> selectionList = Arrays.asList(selections.split(","));
//            queryWrapper.in("id",selectionList);
//        }
//        // Step.2 获取导出数据
//        List<T> exportList = service.list(queryWrapper);
//
//        // Step.3 AutoPoi 导出Excel
//        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//        //设置的filename
//        mv.addObject(NormalExcelConstants.FILE_NAME, title);
//        mv.addObject(NormalExcelConstants.CLASS, clazz);
//
//        ExportParams exportParams=new ExportParams(title + "报表",  title);
//        exportParams.setImageBasePath(upLoadPath);
//
//        mv.addObject(NormalExcelConstants.PARAMS,exportParams);
//        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
//        return mv;
//    }
//    /**
//     * 根据每页sheet数量导出多sheet
//     *
//     * @param request
//     * @param object 实体类
//     * @param clazz 实体类class
//     * @param title 标题
//     * @param exportFields 导出字段自定义
//     * @param pageNum 每个sheet的数据条数
//     * @param request
//     */
//    protected ModelAndView exportXlsSheet(HttpServletRequest request, T object, Class<T> clazz, String title,String exportFields,Integer pageNum) {
//        // Step.1 组装查询条件
//        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
//        // Step.2 计算分页sheet数据
//        double total = service.count();
//        int count = (int)Math.ceil(total/pageNum);
//        // Step.3  过滤选中数据
//        String selections = request.getParameter("selections");
//        if (oConvertUtils.isNotEmpty(selections)) {
//            List<String> selectionList = Arrays.asList(selections.split(","));
//            queryWrapper.in("id",selectionList);
//        }
//        // Step.4 多sheet处理
//        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
//        for (int i = 1; i <=count ; i++) {
//            Page<T> page = new Page<T>(i, pageNum);
//            IPage<T> pageList = service.page(page, queryWrapper);
//            List<T> exportList = pageList.getRecords();
//            Map<String, Object> map = new HashMap<>(5);
//            ExportParams  exportParams=new ExportParams(title + "报表", title+i,upLoadPath);
//            exportParams.setType(ExcelType.XSSF);
//            //map.put("title",exportParams);
//            //表格Title
//            map.put(NormalExcelConstants.PARAMS,exportParams);
//            //表格对应实体
//            map.put(NormalExcelConstants.CLASS,clazz);
//            //数据集合
//            map.put(NormalExcelConstants.DATA_LIST, exportList);
//            listMap.add(map);
//        }
//        // Step.4 AutoPoi 导出Excel
//        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//        //此处设置的filename无效 ,前端会重更新设置一下
//        mv.addObject(NormalExcelConstants.FILE_NAME, title);
//        mv.addObject(NormalExcelConstants.MAP_LIST, listMap);
//        return mv;
//    }
//
//
//    /**
//     * 根据权限导出excel，传入导出字段参数
//     *
//     * @param request
//     */
//    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title,String exportFields) {
//        ModelAndView mv = this.exportXls(request,object,clazz,title);
//        mv.addObject(NormalExcelConstants.EXPORT_FIELDS,exportFields);
//        return mv;
//    }

    /**
     * 获取对象ID
     *
     * @return
     */
    private String getId(T item) {
        try {
            return PropertyUtils.getProperty(item, "id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * 通过excel导入数据
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    protected Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//            // 获取上传文件对象
//            MultipartFile file = entity.getValue();
//            ImportParams params = new ImportParams();
//            params.setTitleRows(2);
//            params.setHeadRows(1);
//            params.setNeedSave(true);
//            try {
//                List<T> list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
//                long start = System.currentTimeMillis();
//                service.saveBatch(list);
//                //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
//                //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
//                log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
//                return Result.ok("文件导入成功！数据行数：" + list.size());
//            } catch (Exception e) {
//                //入数据重复增加提示
//                String msg = e.getMessage();
//                log.error(msg, e);
//                if(msg!=null && msg.indexOf("Duplicate entry")>=0){
//                    return Result.error("文件导入失败:有重复数据！");
//                }else{
//                    return Result.error("文件导入失败:" + e.getMessage());
//                }
//            } finally {
//                try {
//                    file.getInputStream().close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return Result.error("文件导入失败！");
//    }
}
