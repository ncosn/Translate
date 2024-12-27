package com.sgcc.yzd.translate.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.sgcc.yzd.translate.MyApplication;
import com.sgcc.yzd.translate.R;
import com.sgcc.yzd.translate.base.RoundedCornersTransformation;
import com.sgcc.yzd.translate.databinding.ActivityFifthBinding;
import com.sgcc.yzd.translate.databinding.ActivityRobotBinding;
import com.sgcc.yzd.translate.db.AppDatabase;
import com.sgcc.yzd.translate.db.entity.Device;
import com.sgcc.yzd.translate.db.entity.Instruction;
import com.sgcc.yzd.translate.retrofit.Api;
import com.sgcc.yzd.translate.retrofit.ApiMethods;
import com.sgcc.yzd.translate.retrofit.ApiService;
import com.sgcc.yzd.translate.service.HandlerService;
import com.sgcc.yzd.translate.utils.HttpBusinessUtils;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RobotActivity extends AppCompatActivity {

    ActivityRobotBinding binding;

    AppDatabase database;

    List<Instruction> instructions;
    List<Device> devices;

    Map<Integer,Device> deviceMap;

    private Handler handler;
    Boolean isFront = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRobotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BusUtils.register(this);
        // 设置 Glide 请求选项，使用 RoundedCorners 进行圆角转换
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(30));

        Glide.with(this)
                .asGif()
                .load(R.drawable.smile)
                .transform(new RoundedCornersTransformation(this, 8))
                .apply(requestOptions)
                .into(binding.ivSmile);

//        handler = new Handler(getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 100:
//                        break;
//                    case 101:
//                        Intent intent = new Intent(RobotActivity.this,WebViewActivity.class);
//                        startActivity(intent);
//                        break;
//                }
//                super.handleMessage(msg);
//            }
//        };
//        handler.sendEmptyMessage(100);
//        initDatabase();

//        instructions = database.instructionDao().queryAllInstructions();
//        devices = database.deviceDao().queryAllDevices();
//        deviceMap = new HashMap<>();
//        for (Device device : devices) {
//            deviceMap.put(device.getDeviceId(),device);
//        }

        MyApplication application = (MyApplication) getApplication();
        instructions = application.instructions;
        deviceMap = application.deviceMap;

        binding.btPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RobotActivity.this, HandlerService.class);
//                startService(intent);
//                BusUtils.post("platform");
                binding.tvResult.setText("应用配置文件中包含应用配置信息、应用组件信息、权限信息、开发者自定义信息等，这些信息在编译构建、分发和运行解决分别提供给编译工具、应用市场和操作系统使用。在基于Stage模型开发的应用项目代码下，都存在app.json5（一个）及module.json5（一个或多个）两种配置文件，常");
            }
        });
//        binding.btPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                HttpBusinessUtils.postCurtainControl();
////                Instruction instruction = database.instructionDao().queryInstruction("开户");
//                Instruction instruction = null;
//                String words = binding.etText.getText().toString().trim();
//                binding.tvResult.setText(words);
//                for (Instruction i : instructions) {
//                    if (words.contains(i.getKeyWords())) {
//                        instruction = i;
//                        break;
//                    }
//                }
//                if (null == instruction) {
//                    binding.tvResult.append("没听清\n");
//                } else {
//                    switch (instruction.getQueryType()) {
//                        case 0:
//                            binding.tvResult.setText(instruction.getQueryReply());
//                            break;
//                        case 1:
//                        case 2:
//                        case 3:
////                            if (deviceMap == null) {
////                                deviceMap = new HashMap<>();
////                                for (Device device : devices) {
////                                    deviceMap.put(device.getDeviceId(),device);
////                                }
////                                break;
////                            }
//                            List<Integer> deviceIds = Arrays.stream(instruction.getDeviceId().split(","))
//                                    .map(Integer::parseInt)
//                                    .collect(Collectors.toList());
//                            for (Integer deviceId : deviceIds) {
//                                Device device = deviceMap.get(deviceId);
//                                binding.tvResult.append(device.getDeviceAlias());
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        });
    }

    @Override
    protected void onResume() {
        isFront = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isFront = false;
        super.onPause();
    }

    @BusUtils.Bus(tag = "platform")
    public void jumpToPlatform() {
        ToastUtils.showShort("deqddqewdqwd");
        binding.btPost.setText("dwdwdqd");
        if (isFront) {
            Intent intent = new Intent(RobotActivity.this,WebViewActivity.class);
            startActivity(intent);
        }
    }

    public void initDatabase() {
        database = AppDatabase.getInstance(this);
        Device device = new Device(198,"CL_1",1,"CURTAIN","窗帘1","大堂");
        insertDevice(device);
        device = new Device(194,"JC_1",1,"DETECTOR","环境监测仪-设备","大堂");
        insertDevice(device);
        device = new Device(179,"KT_1",1,"CONDITIONER","空调1","大堂");
        insertDevice(device);
        device = new Device(192,"KT_2",2,"CONDITIONER","空调2","大堂");
        insertDevice(device);
        device = new Device(193,"KT_3",3,"CONDITIONER","空调3","大堂");
        insertDevice(device);
        device = new Device(180,"KT_4",4,"CONDITIONER","空调4","大堂");
        insertDevice(device);
        device = new Device(181,"KT_5",5,"CONDITIONER","空调5","大堂");
        insertDevice(device);
        device = new Device(190,"YK_1",1,"SWITCH","VTM终端","自助服务区");
        insertDevice(device);
        device = new Device(183,"YK_10",10,"SWITCH","PC版柜台条形屏","业务服务区");
        insertDevice(device);
        device = new Device(184,"YK_11",11,"SWITCH","主照明灯","大堂");
        insertDevice(device);
        device = new Device(178,"YK_12",12,"SWITCH","大屏灯带","自助服务区");
        insertDevice(device);
        device = new Device(185,"YK_13",13,"SWITCH","环境监测仪","大堂");
        insertDevice(device);
        device = new Device(186,"YK_14",14,"SWITCH","地标投影","大堂");
        insertDevice(device);
        device = new Device(187,"YK_15",15,"SWITCH","桌面终端柜台条形屏","业务服务区");
        insertDevice(device);
        device = new Device(188,"YK_16",16,"SWITCH","综合叫号屏","业务服务区");
        insertDevice(device);
        device = new Device(191,"YK_2",2,"SWITCH","云终端","自助服务区");
        insertDevice(device);
        device = new Device(197,"YK_3",3,"SWITCH","电视大屏","大堂");
        insertDevice(device);
        device = new Device(172,"YK_4",4,"SWITCH","电价发布屏","大堂");
        insertDevice(device);
        device = new Device(173,"YK_5",5,"SWITCH","PC版柜台","业务服务区");
        insertDevice(device);
        device = new Device(174,"YK_6",6,"SWITCH","预受理终端","大堂");
        insertDevice(device);
        device = new Device(175,"YK_7",7,"SWITCH","讲台插座","大堂");
        insertDevice(device);
        device = new Device(176,"YK_8",8,"SWITCH","桌面终端柜台","业务服务区");
        insertDevice(device);
        device = new Device(177,"YK_9",9,"SWITCH","信息发布屏","业务服务区");
        insertDevice(device);

        Instruction instruction;
        instruction = new Instruction(0,"你是谁",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"你叫什么",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍自己",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下自己",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"自我介绍",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"你能做什么",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"你有哪些功能",null,null,null, getResources().getString(R.string.self_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"电力行业",null,null,null, getResources().getString(R.string.electric_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下公司",null,null,null, getResources().getString(R.string.company_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"思行达",null,null,null, getResources().getString(R.string.company_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"展厅",null,null,null, getResources().getString(R.string.exhibition_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍营业厅",null,null,null, getResources().getString(R.string.exhibition_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下营业厅",null,null,null, getResources().getString(R.string.exhibition_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍预受理",null,null,null, getResources().getString(R.string.pre_accepted_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下预受理",null,null,null, getResources().getString(R.string.pre_accepted_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍云终端",null,null,null, getResources().getString(R.string.yzd_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下云终端",null,null,null, getResources().getString(R.string.yzd_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下网上国网云终端",null,null,null, getResources().getString(R.string.yzd_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍综合业务终端",null,null,null, getResources().getString(R.string.integrated_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下综合业务终端",null,null,null, getResources().getString(R.string.integrated_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下VTM终端",null,null,null, getResources().getString(R.string.integrated_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下现金终端",null,null,null, getResources().getString(R.string.integrated_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下移动终端",null,null,null, getResources().getString(R.string.mobile_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍移动终端",null,null,null, getResources().getString(R.string.mobile_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下大屏",null,null,null, getResources().getString(R.string.screen_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍大屏",null,null,null, getResources().getString(R.string.screen_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下监控看板",null,null,null, getResources().getString(R.string.screen_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下数据看板",null,null,null, getResources().getString(R.string.screen_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下柜外清",null,null,null, getResources().getString(R.string.external_interactive_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍柜外交互终端",null,null,null, getResources().getString(R.string.external_interactive_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍柜外清",null,null,null, getResources().getString(R.string.external_interactive_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍桌面终端",null,null,null, getResources().getString(R.string.desktop_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下桌面终端",null,null,null, getResources().getString(R.string.desktop_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍一下桌面服务终端",null,null,null, getResources().getString(R.string.desktop_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"介绍桌面服务终端",null,null,null, getResources().getString(R.string.desktop_service_terminal_introduction));
        insertInstruction(instruction);
        instruction = new Instruction(0,"我要缴费",null,null,null, getResources().getString(R.string.business_pay));
        insertInstruction(instruction);
        instruction = new Instruction(0,"交电费",null,null,null, getResources().getString(R.string.business_pay));
        insertInstruction(instruction);
        instruction = new Instruction(0,"我要现金缴费",null,null,null, getResources().getString(R.string.business_pay));
        insertInstruction(instruction);
        instruction = new Instruction(0,"我要开户",null,null,null, getResources().getString(R.string.business_open_account));
        insertInstruction(instruction);
        instruction = new Instruction(0,"办理开户",null,null,null, getResources().getString(R.string.business_open_account));
        insertInstruction(instruction);
        instruction = new Instruction(0,"我要过户",null,null,null, getResources().getString(R.string.business_transfer));
        insertInstruction(instruction);
        instruction = new Instruction(0,"办理过户",null,null,null, getResources().getString(R.string.business_transfer));
        insertInstruction(instruction);
        instruction = new Instruction(0,"我要更名",null,null,null, getResources().getString(R.string.business_rename));
        insertInstruction(instruction);
        instruction = new Instruction(0,"办理更名",null,null,null, getResources().getString(R.string.business_rename));
        insertInstruction(instruction);
        instruction = new Instruction(0,"打印发票",null,null,null, getResources().getString(R.string.bill_print));
        insertInstruction(instruction);
        instruction = new Instruction(0,"电子发票",null,null,null, getResources().getString(R.string.electric_bill));
        insertInstruction(instruction);

        instruction = new Instruction(1,"开启PC版柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开PC版柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭PC版柜台",4,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"开启综合业务柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开综合业务柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭综合业务柜台",4,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开综合柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"开启综合柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭综合柜台",3,"173","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开条形屏",3,"183","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭条形屏",4,"183","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开窗口条形屏",3,"183","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭窗口条形屏",4,"183","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开综合业务终端",3,"190","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭综合业务终端",4,"190","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开现金终端",3,"190","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭现金终端",4,"190","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开VTM终端",3,"190","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开VTM终端",4,"190","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"开灯",3,"184","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关灯",4,"184","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭灯光",4,"184","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开主照明灯",3,"184","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭主照明灯",4,"184","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开云终端",3,"191","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭云终端",4,"191","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开信息发布屏",3,"177","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭信息发布屏",4,"177","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开地标投影",3,"186","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开地标投影",4,"186","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开大屏灯带",3,"178","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭大屏灯带",4,"178","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开灯带",3,"178","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭灯带",4,"178","自助服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开桌面终端柜台",3,"176","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭桌面终端柜台",4,"176","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开桌面终端柜台条形屏",3,"187","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭桌面终端柜台条形屏",4,"187","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开环境监测仪",3,"185","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭环境监测仪",4,"185","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开电价发布屏",3,"172","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭电价发布屏",4,"172","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开电视",3,"197","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭电视",4,"197","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(3,"打开空调",1,"179,180","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(3,"关闭空调",2,"179,180","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(2,"打开窗帘",1,"198","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(2,"关闭窗帘",2,"198","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开综合叫号屏",3,"188","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭综合叫号屏",4,"188","业务服务区", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开讲台插座",3,"175","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭讲台插座",4,"175","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"打开预受理终端",3,"174","大堂", null);
        insertInstruction(instruction);
        instruction = new Instruction(1,"关闭预受理终端",4,"174","大堂", null);
        insertInstruction(instruction);

    }

    public void insertInstruction(Instruction instruction) {
        database.instructionDao().insertInstruction(instruction);
    }

    public void insertDevice(Device device) {
        database.deviceDao().insertDevice(device);
    }

    @Override
    protected void onDestroy() {
        BusUtils.unregister(this);
        super.onDestroy();
    }
}