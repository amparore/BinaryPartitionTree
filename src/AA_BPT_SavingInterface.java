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

//package examples.standard.sequential;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import datastructure.Node;
import datastructure.Tree;
import metric.color.MixedColorArea;
import standard.sequential.BPT;
//import utils.ImTool;
//import utils.SaveBPT;

/**
 * Example saving a created BPT in a file having .bpt format.
 *
 */
public class AA_BPT_SavingInterface {

	public static void main(String[] args) {
				
//		args = new String[]{//"../../imgs/bird3.png",
//							//"xp/examples/six_regions_3_3.png",
//							"../../imgs/egret.png",
////						    "../O.png",
//							"../__bpt_cache__/test.bpt",
//							"0.50"};
		
		if (args.length != 3) {
			System.out.println("Usage:  java -jar BPT.jar  <image_filename>  <out.bpt>  <alpha>");
			return;
		}
		

		boolean success = false;
		
		try {
			File fin = new File(args[0]);
			if (!fin.exists()) {
				System.out.println("Cannot read input file "+fin);
			}
			File fout = new File(args[1]);
			
			double alpha = Double.parseDouble(args[2]);
			
			
//			TypeOfMetric[] metrics = new TypeOfMetric[]{ TypeOfMetric.MIXED_COLOR_AREA, null }; 
//			int m = 0;
//			int consensusRange = 5; /* percentage defining the interval of the list to consider */
//			int progressive = 3; /* the interval is defined proportionally to remaining number of adjacency links */
//			ConsensusStrategy consStrat = ConsensusStrategy.SCORE_OF_RANK;
//			
//			// read extra arguments
//			for (int i=2; i<args.length; i++) {
//				
//				TypeOfMetric tm = getTypeOfMetric(args[i]);
//				if (tm !=null) {
//					metrics[m++] = tm;
//					continue;
//				}
//				
//				ConsensusStrategy cs = getConsensusStrategy(args[i]);
//				if (cs != null) {
//					consStrat = cs;
//					continue;
//				}
//				
//				if (args[i].equals("-cr") && i+1 < args.length) {
//					consensusRange = Integer.parseInt(args[++i]);
//					continue;
//				}
//				else if (args[i].equals("-p") && i+1 < args.length) {
//					progressive = Integer.parseInt(args[++i]);
//					continue;
//				}
//			}
//			
//
			BufferedImage image = ImageIO.read(fin);
			Tree bptTree = new BPT(image);
			bptTree.setMetric(new MixedColorArea(image, alpha));

//			
//			// Create BPT tree
//			Tree bptTree;
//			
//			if (metrics[1] == null) { // single metric
//				bptTree = new BPT(image);
//				bptTree.setMetric(MetricFactory.initMetric(metrics[0], image));			
//			}
//			else { // multiple metrics + consensus
//				bptTree = new MBPT();
//				((MBPT) bptTree).registerImage(image);
//				((MBPT) bptTree).setConsensusStrategy(consStrat, consensusRange, progressive);
//				((MBPT) bptTree).linkMetricToAnImage(image, metrics[0]);
//				((MBPT) bptTree).linkMetricToAnImage(image, metrics[1]);
//			}
//			
			// Build the BPT
			bptTree.grow();
			
			
//			Tree bpt = new BPT(image);
//			bpt.setMetric(new RadiometricAverage(image));
////			bpt.setPreSegImage(presegImage);
//			bpt.grow();
////			System.out.println(bpt.getMetric());
			
//			Tree bptTree = new MBPT();
//			
//			/* Register the image(s) */
//			((MBPT) bptTree).registerImage(image);
//
//			/* Choosing the consensus strategy to use */
//			int consensusRange = 5; /* percentage defining the interval of the list to consider */
//			int progressive = 3; /* the interval is defined proportionally to remaining number of adjacency links */
//			((MBPT) bptTree).setConsensusStrategy(ConsensusStrategy.SCORE_OF_RANK, 
//												  consensusRange, progressive);
//			
//			/* Linking metrics to the image */
//			((MBPT) bptTree).linkMetricToAnImage(image, TypeOfMetric.RADIOMETRIC_MIN_MAX);
////			((MBPT) bptTree).linkMetricToAnImage(image, TypeOfMetric.);
//			((MBPT) bptTree).linkMetricToAnImage(image, TypeOfMetric.OCONTOUR);

			
			
			if(bptTree != null && bptTree.hasEnded()) {
//				long time = System.currentTimeMillis();
				TreeEncoder encoder =  new TreeEncoder(bptTree);
				encoder.build();
				
				PrintWriter writer = new PrintWriter(fout);
				encoder.write(writer);
				writer.close();
				
//				time = System.currentTimeMillis() - time;
//				System.out.println("Saving time: "+time/1000.0);
				success = true;
				if(success)	{
					System.out.println("BPT saved successfully! ");
				}
			}
						
		}catch(Exception e){
			
			e.printStackTrace();
		}
				
		System.exit(success ? 0 : 1);
	}
	
	// Save the BPT as a text file
	static class TreeEncoder {
		Tree bpt;
		int[] pixels;
		int pp;
		int[] s_starts, s_ends;
		int[] n_lefts, n_rights;

		public TreeEncoder(Tree bpt) {
			super();
			this.bpt = bpt;
		}
		
		public void build() {
			pixels = new int[bpt.getNbLeaves()];
			s_starts = new int[bpt.getNbNodes()];
			s_ends = new int[bpt.getNbNodes()];
			n_lefts = new int[bpt.getNbNodes()];
			n_rights = new int[bpt.getNbNodes()];
			pp = 0;
			visitTree(bpt.getRoot());
			assert(pp == bpt.getNbLeaves());
			

		}
		
		public void write(PrintWriter writer) throws IOException 
		{
			writer.println(bpt.getImage().getWidth());
			writer.println(bpt.getImage().getHeight());
			writer.println(bpt.getNbLeaves());
			writer.println(bpt.getNbNodes());

			// print pixel list
			for (int i=0; i<pixels.length; i++) {
				writer.print(pixels[i]+" ");
			}
			writer.println();
			
			// print leaves
			for (int i=0; i<+bpt.getNbLeaves(); i++) {
				writer.print(s_starts[i]+" ");
			}
			writer.println();
			
			// print clusters
			int base = bpt.getNbLeaves();
			for (int i=base; i<bpt.getNbNodes(); i++) {
				writer.print(s_starts[i]+" ");
			}
			writer.println();
			for (int i=base; i<bpt.getNbNodes(); i++) {
				writer.print(s_ends[i]+" ");
			}
			writer.println();
			for (int i=base; i<bpt.getNbNodes(); i++) {
				writer.print(n_lefts[i]+" ");
			}
			writer.println();
			for (int i=base; i<bpt.getNbNodes(); i++) {
				writer.print(n_rights[i]+" ");
			}
			writer.println("\n");
		}
		
		private
		int visitTree(Node node) {
			int cc = node.name;
			if(node.leftNode == null) { // visiting single pixel
				pixels[pp] = node.name;
				s_starts[cc] = pp;
				s_ends[cc] = pp + 1;
				n_lefts[cc] = n_rights[cc] = -1;
				pp++;
			}
			else {
				s_starts[cc] = pp;
				n_lefts[cc] = visitTree(node.leftNode);
				n_rights[cc] = visitTree(node.rightNode);
				s_ends[cc] = pp;
			}
//			System.out.println("cluster "+cc+" starts at "+s_starts[cc]+", ends at "+s_ends[cc]+
//					   " splits into ["+n_lefts[cc]+","+n_rights[cc]+"]");
			return cc;
		}
	}
}
