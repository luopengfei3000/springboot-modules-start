package com.example.springbootjsp.controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.springbootjsp.entity.Photo;
import com.example.springbootjsp.entity.User;
import com.example.springbootjsp.page.QueryRespBean;
import com.example.springbootjsp.service.PhotoService;
import com.example.springbootjsp.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Encoder;

/**
 * ****科技有限责任公司 定义 用户控制层
 *
 * @author luopf
 * @data 2019/1/17
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    static final int pageSize = 10;
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    /**
     * 查询用户测试数据
     * 
     * @param request
     * @param response
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author luopf 2019/1/17
     */
    @ResponseBody
    @RequestMapping(value = "/allPage", produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> findAllUserByPage(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        QueryRespBean<User> result = null;
        String pageNum = request.getParameter("pageNum");
        int pageNumber = 0;
        if (null == pageNum) {
            pageNumber = 1;
        }
        result = userService.searchUserinfoByPage(pageNumber, pageSize);
        map.put("total", result.getPageParameter().getTotalCount());
        map.put("rows", result.getResult());
        return map;
    }

    /**
     * 跳转到用户管理页面
     * 
     * @param request
     * @param reponse
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/toUserinfoManage")
    public ModelAndView toPmsInvoiceFRManage(HttpServletRequest request, HttpServletResponse reponse) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/userinfoManage");
        request.setAttribute("url", "/user/operation/");
        return mav;
    }

    /**
     * 按条件用户信息分页查询
     * 
     * @param request
     * @return java.util.Map
     * @Author luopf 2019/1/17
     */
    @PostMapping(value = "/operation/getUserinfoByPage")
    @ResponseBody
    public Map userinforlistByPage(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        QueryRespBean<User> result = null;
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));// pageSzie
        result = userService.searchUserinfoByPage(page, pageSzie);
        map.put("total", result.getPageParameter().getTotalCount());
        map.put("rows", result.getResult());
        return map;
    }

    /**
     * 根据id跳转到添加/编辑页面
     * 
     * @param type
     * @param id 
     * @param request
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/operation/{type}/{id}")
    public ModelAndView toOpertionPage(@PathVariable String type, @PathVariable String id, HttpServletRequest request)
        throws Exception {
        ModelAndView mav = new ModelAndView();
        if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
            User dto = userService.queryUserinfoByPrimaryKey(id);
            request.setAttribute("user", dto);
        }
        mav.setViewName("/user/" + "userinfo" + type);
        return mav;
    }

    /**
     * 保存数据
     * 
     * @param request
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/operation/save", method = RequestMethod.POST)
    @ResponseBody
    // 返回ModelAndView有问题 求解？？？
    public Map<String, Object> toSaveUserDTO(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            User user = JSON.parseObject(jsonData, User.class);
            if (StringUtils.isBlank(user.getUserId())) {// 新增
                userService.insertUserinfo(user);
            } else {// 修改
                userService.updateUserinfoSensitive(user);
            }
            map.put("user", user);
            map.put("flag", "success");
        } catch (Exception ex) {
            map.put("error", ex.getMessage());
            map.put("flag", "fail");
            return map;
        }
        return map;

    }

    /**
     * 删除数据
     * 
     * @param ids
     * @param request 
     * @param session
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author luopf 2019/1/17
     */
    @RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
    @ResponseBody
    // 一定加ResponseBody 否则报错
    public Map<String, Object> toDeleteUserinfoDTO(@RequestBody String[] ids, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            /*for (String id : ids) {
                if (((User)session.getAttribute("user")).getUserId().equals(id)) {
                    map.put("msg", "违法操作！不能删除自己！");
                    return map;
                }
            }*/
            userService.deleteUserinfoByIds(ids);
            map.put("flag", "success");
        } catch (Exception ex) {
            map.put("error", ex.getMessage());
            map.put("flag", "fail");
            return map;
        }
        return map;
    }

    /**
     * 修改自己信息
     * 
     * @param userId
     * @param userName 
     * @param password 
     * @param phone 
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/updateMyInfo", method = {RequestMethod.POST, RequestMethod.GET}) // 保存新增用户和修改的用户信息
    @ResponseBody
    public ModelAndView updateMyInfo(@RequestParam("userId") String userId, @RequestParam("userName") String userName,
        @RequestParam("password") String password, @RequestParam("phone") String phone, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(userName);
        user.setPhone(phone);
        try {
            userService.updateUserinfoSensitive(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/error");
        return model;
    }

    /**
     * 跳转到个人信息修改页面
     * 
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "myInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView myInfo(HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = (User)session.getAttribute("user");
        User logUser = userService.findUserinfoByloginInfo(user);
        session.setAttribute("user", logUser);
        model.addObject("user", logUser);
        model.setViewName("/common/myInfo");
        return model;
    }

    /**
     * 修改密码
     * 
     * @param userId
     * @param oldpassword 
     * @param newpassword1 
     * @param newpassword2 
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/modifypassword", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView modifypassword(@RequestParam("userId") String userId,
        @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword1") String newpassword1,
        @RequestParam("newpassword2") String newpassword2, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User Loginuser = (User)session.getAttribute("user");
        if (oldpassword == null || "".equals(oldpassword)) {
            model.addObject("messag", "初始密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword1 == null || "".equals(newpassword1)) {
            model.addObject("messag", "新密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword2 == null || "".equals(newpassword2)) {
            model.addObject("messag", "确认密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (!newpassword2.equals(newpassword1)) {
            model.addObject("messag", "两次密码不一致");
            model.setViewName("/common/success");
            return model;
        } else if (!Loginuser.getPassword().equals(oldpassword)) {
            model.addObject("messag", "初始密码不正确");
            model.setViewName("/common/success");
            return model;
        }

        User user = new User();
        user.setUserId(userId);
        user.setPassword(newpassword2);
        try {
            userService.updateUserinfoSensitive(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/success");
        return model;
    }

    /**
     * 退出登录
     * @param session
     * @return org.springframework.web.servlet.ModelAndView
     * @Author luopf 2019/1/18
     */
    @RequestMapping(value = "/exit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView exit(HttpSession session) {
        session.removeAttribute("user");
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/login/");
        return model;
    }

    @RequestMapping(value = {"/operation/upload/headerphoto/{userId}"}, method = {RequestMethod.GET})
    public void getUploadUserHeaderPhotoMessage(@PathVariable String userId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/*");
        File file;
        BufferedInputStream bis = null;
        ServletOutputStream out = null;
        ByteArrayInputStream bais;
        String serverpath= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ");
        String path=serverpath.substring(1);//从路径字符串中取出工程路径
        User user = userService.findUserinfoUnionPhotoByUserId(userId);
        if (user == null) {
            file = new File(path+"/images/userPhoto.gif");
            if (file.exists()) {
                FileInputStream in = new FileInputStream(file);
                bis = new BufferedInputStream(in);
                int len = in.available();
                if (len > 0) {
                    IOUtils.copy(bis, response.getOutputStream());
                }
                bis.close();
                in.close();
            }
        }else {
                try {
                    //将二进制转换为图片
                    byte[] bytes = Base64.decodeBase64((byte[]) user.getPhoto());
                    bais = new ByteArrayInputStream(bytes);
                    bis = new BufferedInputStream(bais);
                    IOUtils.copy(bis, response.getOutputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    bis.close();
                }

            /*try {
                byte[] byteAry = (byte[])user.getPhoto();
                String data = new String(byteAry,"UTF-8");
                BASE64Decoder dncoder = new BASE64Decoder();
                byte[] bytes = dncoder.decodeBuffer(data);
                for (int i = 0; i < bytes.length; ++i) {
                    if(bytes[i] < 0){
                        bytes[i] += 256;
                    }
                }
                response.setContentType("image/*");
                out = response.getOutputStream();
                out.write(bytes);

            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }*/
        }

        /*//resut = this.userapi.getSysUserByIdforupload(userId);
        if (null != resut && null != resut.getDto2() && ((byte[])resut.getDto2()).length > 0) {
            bis = null;

            try {
                bais = new ByteArrayInputStream((byte[])resut.getDto2());
                bis = new BufferedInputStream(bais);
                IOUtils.copy(bis, response.getOutputStream());
            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                try {
                    bis.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }

            }
        } else {
            path = request.getSession().getServletContext().getRealPath("/");
            String lastChar = path.charAt(path.length() - 1) + "";
            if (!lastChar.equals(FileUtil.getSep())) {
                path = path + FileUtil.getSep();
            }

            file = new File(path + PHOTO_PATH);
            if (file.exists()) {
                in = null;
                BufferedInputStream bis = null;
                in = new FileInputStream(file);
                bis = new BufferedInputStream(in);
                int len = in.available();
                if (len > 0) {
                    IOUtils.copy(bis, response.getOutputStream());
                }

                bis.close();
                in.close();
            }
        }*/
    }

    /**
     * 上传图片
     * @param response
     * @param request
     * @param file
     * @return java.lang.String
     * @Author luopf 2019/2/20
     */
    @RequestMapping(value = "/operation/uploadimg/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@PathVariable String userId, HttpServletResponse response, HttpServletRequest request,@RequestParam("sysUserPhoto") MultipartFile file) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            request.setCharacterEncoding("utf-8");
            if(!file.isEmpty()){
                BASE64Encoder encoder = new BASE64Encoder();
                String image = encoder.encode(file.getBytes());
                Photo photoDTO = photoService.selectPhotoinfoByUserId(userId);
                if (photoDTO == null) {
                    Photo photo = new Photo();
                    photo.setUserId(userId);
                    photo.setPhoto(image);
                    photoService.insertPhotoinfo(photo);
                }else {
                    photoDTO.setPhoto(image);
                    photoService.UpdatePhotoinfo(photoDTO);
                }
            }
        } catch (Exception e) {
            return map;
        }
        return map;
    }

}
