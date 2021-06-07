package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * 作者： 刘路 描述：播放控制Panel 修改时间：Nov 22, 2019 备注信息： 版本：1.0.0 ©copyright by Liulu
 * 2019-2069
 */
public class PlayControl_Panel {
	// panel
	public static JPanel panel = null;
	private static final int MIN_PROGRESS = 0; //
	private static final int MAX_PROGRESS = 100; //
	private static int currentProgress = MIN_PROGRESS;// 当前进度
	private static JProgressBar progressBar = new JProgressBar();
	private static Timer t;

	public static void play() {
		// 模拟延时操作进度, 每隔 0.5 秒更新进度
		
		t.start();
	};

	public static void rePlay() {
		currentProgress = MIN_PROGRESS;
		t.start();
		
	}
	public static void pause() {
		t.stop();
	};

	public static void stop() {
		t.stop();
	};

	/**
	 * 
	 * 描述：生成一个panel 5:01:39 PM 输入： 输出： 返回值：
	 */
	public static void createPanel() {
		panel = new JPanel();
		// 设置进度的 最小值 和 最大值
		progressBar.setMinimum(MIN_PROGRESS);
		progressBar.setMaximum(MAX_PROGRESS);

		// 设置当前进度值
		progressBar.setValue(currentProgress);
		// 设置边界
		progressBar.setBorderPainted(false);
		// 设置背景颜色
		progressBar.setBackground(Color.white);
		// 绘制百分比文本（进度条中间显示的百分数）
		// progressBar.setStringPainted(true);

		// 添加进度改变通知
		/*
		 * progressBar.addChangeListener(new ChangeListener() {
		 * 
		 * @Override public void stateChanged(ChangeEvent e) {
		 * System.out.println("当前进度值: " + progressBar.getValue() + "; " +
		 * "进度百分比: " + progressBar.getPercentComplete()); } });
		 */

		// 设置进度条的长度
		progressBar.setPreferredSize(new Dimension(680, 5));
		
		t = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentProgress++;
				if (currentProgress > MAX_PROGRESS) {
					currentProgress = MIN_PROGRESS;
				}
				progressBar.setValue(currentProgress);
			}
		});
		t.stop();
		panel.add(progressBar);
	}


}
