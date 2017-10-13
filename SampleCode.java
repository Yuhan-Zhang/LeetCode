//Sample Code
//It's a online assessment I recently finished. 
//I chose the problem because it envolves several kinds of data structures and algorithm,
//which shows I have certain understanding of data structures and algorithms and have good coding styles

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Map;
import java.util.*;
import java.lang.reflect.Array;

public class cheeseProblem {
	
	public static void main(String[] args){

		int[][] sample = {{0,2,0}, {1,1,2}, {1,0,0}};			   	

					 int x=2;
					 int y=1;

		System.out.println(findMinimumPath(sample,x,y,0,0));
	}

	public static int findMinimumPath(int[][] arr,int x,int y,int startX,int startY){

		ArrayList<Point> arrList = new ArrayList<>();
		for(int i=0; i < arr.length;i++){
			for(int j=0; j < arr.length;j++){
				if(arr[i][j]==2){
					arrList.add(new Point(i,j));
				}	
			}
		}
		int minimumDistance=Integer.MAX_VALUE;
			
		int newStartX=0;
		int newStartY=0;

		int id=-1;

		int steps=0;
		int dist=0;
		while(arrList.size()!=0){
			minimumDistance=Integer.MAX_VALUE;
			for(int i=0; i < arrList.size();i++){
				Point p = arrList.get(i);
				dist = findMinimumDistance(new Point(startX,startY),new Point(p.x,p.y),arr);
				if(dist ==-1){ return -1;}

				if(dist < minimumDistance){
							newStartX=p.x;
							newStartY=p.y;
							id=i;
							minimumDistance=dist;
				}
		  }
				
			steps=steps+minimumDistance;
			arrList.remove(id);
			startX=newStartX;
			startY=newStartY;
		}
	dist = findMinimumDistance(new Point(startX,startY),new Point(x,y),arr);
			return dist==-1 ? -1 : steps + dist;
  }


	public static int findMinimumDistance(Point source,Point destination,int[][] arr){

		    int[] dis = {0, 1, 0, -1, 0};
		    
		    Queue<QueueNode> queue = new LinkedList<>();
		    queue.add(new QueueNode(source,0));	

		    boolean[][] visited = new boolean[arr.length][arr.length];

		    visited[source.x][source.y]=true;

		    while(!queue.isEmpty()){
		    	QueueNode node = queue.poll();
		    	Point currentPoint = node.point;

		    	if(currentPoint.x==destination.x && currentPoint.y==destination.y){
		    		return node.distance;
		    	}else{
		    		for(int i=0; i < dis.length - 1;i++){
		    			int possibleX = currentPoint.x + dis[i];
		    			int possibleY = currentPoint.y + dis[i + 1];
		    			if(isValid(possibleX,possibleY,arr) && !visited[possibleX][possibleY] && arr[possibleX][possibleY]!=1){
		    				visited[possibleX][possibleY]=true;
		    				queue.add(new QueueNode(new Point(possibleX,possibleY),node.distance+1));
		    			}
		    		}
		    	}
		    }

		return -1;
	}

    public static boolean isValid(int possibleX,int possibleY,int[][] arr){
        return(possibleX < arr.length && possibleX >=0 && possibleY < arr[0].length && possibleY >=0 );
    }
}
class Point{

	int x;
	int y;
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}

}
class QueueNode{

	Point point;
	int distance;

	public QueueNode(Point point,int distance){
		this.point = point;
		this.distance=distance;
	}
}
