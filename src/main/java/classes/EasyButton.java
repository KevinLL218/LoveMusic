package classes;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *  作者： 刘路
 *	描述：生成一个简单的JButton对象
 *	修改时间：Nov 22, 2019
 *	备注信息：
 *	版本：1.0.0
 *	©copyright by Liulu 2019-2069
 */
public class EasyButton extends JButton{
	/**
	 * 描述：create a button
	 * 8:24:03 PM
	 * 输入：名称，默认图标，按下后的图标
	 * 输出：
	 * 返回值：
	 */
	public EasyButton(String name,String defaultIcon,String pressedIcon){
		setContentAreaFilled(false);    //不绘制默认按钮背景
	    setFocusPainted(false);           //不绘制图片或文字周围的焦点虚框
	    setIcon(new ImageIcon(defaultIcon));
	    setPressedIcon(new ImageIcon(pressedIcon));
	    setBorderPainted(false);   //do not paint the border
		
	}
}
