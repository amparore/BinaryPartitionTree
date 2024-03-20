/****************************************************************************
* Copyright AGAT-Team (2014)						       
* 									    
* Contributors:								
* J.F. Randrianasoa							    
* K. Kurtz								    
* E. Desjardin								    
* N. Passat								    
* 									    
* This software is a computer program whose purpose is to [describe	    
* functionalities and technical features of your software].		    
* 									    
* This software is governed by the CeCILL-B license under French law and    
* abiding by the rules of distribution of free software.  You can  use,     
* modify and/ or redistribute the software under the terms of the CeCILL-B  
* license as circulated by CEA, CNRS and INRIA at the following URL	    
* "http://www.cecill.info". 						    
* 									    
* As a counterpart to the access to the source code and  rights to copy,    
* modify and redistribute granted by the license, users are provided only   
* with a limited warranty  and the software's author,  the holder of the    
* economic rights,  and the successive licensors  have only  limited	    
* liability. 								    
* 									    
* In this respect, the user's attention is drawn to the risks associated    
* with loading,  using,  modifying and/or developing or reproducing the     
* software by the user in light of its specific status of free software,    
* that may mean  that it is complicated to manipulate,  and  that  also	   
* therefore means  that it is reserved for developers  and  experienced     
* professionals having in-depth computer knowledge. Users are therefore     
* encouraged to load and test the software's suitability as regards their   
* requirements in conditions enabling the security of their systems and/or  
* data to be ensured and,  more generally, to use and operate it in the     
* same conditions as regards security. 					    
*								            
* The fact that you are presently reading this means that you have had	    
* knowledge of the CeCILL-B license and that you accept its terms.          
* 									   		
* The full license is in the file LICENSE, distributed with this software.  
*****************************************************************************/

package metric.color;

import java.awt.image.BufferedImage;

import datastructure.Node;


public class MixedColorArea extends Ominmax {

	// mixing coefficient
	private double alpha;
	
	// image area
	private double num_image_pixels;

	public MixedColorArea(BufferedImage image, double alpha) {
		super(image);
		this.alpha = alpha;
		this.num_image_pixels = image.getWidth() * image.getHeight();
//		System.out.println("Using MixedColorArea.");
	}
	
	@Override
	public double computeDistances(Node n1, Node n2) {
		
		double colorScore = (super.computeDistances(n1, n2) + 1) / (768.0) * alpha; // / 100.0;
		double areaScore = computeAreaScore(n1, n2) * (1.0 - alpha);
		
//		System.out.println(colorScore+" * "+areaScore);
		
		return colorScore * areaScore;
	}
	
	private double computeAreaScore(Node n1, Node n2) {
		double area1 = n1.getPixels().size() / num_image_pixels;
		double area2 = n2.getPixels().size() / num_image_pixels;

//		return (Math.abs(area1 - area2) + 1.0/num_image_pixels);
//		return (Math.abs(area1 - area2) + 1) + (area1 + area2);
		
		
//		return Math.min(area1, area2) / Math.max(area1, area2) * (area1 + area2);
		return area1 + area2;
//		return n1.getPixels().size() + n2.getPixels().size();
	}

	
}
