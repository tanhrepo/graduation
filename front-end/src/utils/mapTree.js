/**
 * 将一维的扁平数组转换为多层级对象
 * @param  {[type]} list 一维数组，数组中每一个元素需包含id和parent_id两个属性
 * @return {[type]} list 多层级树状结构
 * @date 2021-04-20 11:06
 */

// 将相同父级的元素放到2级
export function buildTree(data){
  data.reverse()
  let TreeData=[];
  let map = new Map(); //存在id,对应所在的内存地址
  let outputObj,parentId;
  for(let i = 0; i<data.length;i++){
    parentId = data[i].parentId;
    if(map.has(parentId)){
      //存在，将些信息，加入到对应id=parentId的对象上的children
      if (!map.get(parentId).children)
        map.get(parentId).children = [];
      let obj = new Object(data[i]);
      map.get(parentId).children.push(obj);
      map.set(data[i].id,obj);
    }else if(!map.has(parentId)){
      //这里处理parentId不存在
      outputObj = new Object(data[i]);
      TreeData.push(outputObj);
      map.set(data[i].id,outputObj);
    }
  }
  return TreeData;
}