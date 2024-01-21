<template>
  <div id="app">
    <h1>Producer/Consumer Simulation</h1>
    <h2>Unleash the gears!</h2>
    <div class="tool-wrapper">
      <span class="node-add">
        <div class="node-add queue" @click="this.newNodeType = 1; this.queuesNumber +=1; addNode()">
          <div class="button-icon">
          <lord-icon
              src="https://cdn.lordicon.com/vasuakrs.json" 
              trigger="hover"
              stroke="bold"
              colors="primary:#ffffff,secondary:#e8e230"
              style="width:40px;height:40px">
          </lord-icon>
        </div>
        <div>
            Add Queue
        </div>
        </div>
      </span>
      <span class="node-add">
        <div class="node-add machine" @click="this.newNodeType = 0; this.machinesNumber +=1; addNode()">
          <div class="button-icon">
          <lord-icon
              src="https://cdn.lordicon.com/jeycryzx.json"     
              trigger="hover"
              stroke="bold"
              colors="primary:#ffffff,secondary:#08a88a"
              style="width:40px;height:40px">
          </lord-icon>
        </div>
        <div>
            Add Machine
        </div>
        </div>
      </span>
      <!-- <select v-model="newNodeType">
        <option v-for="(item, index) in nodeCategory" :key="index" :value="index">{{item}}</option>
      </select> -->
      <input type="text" v-model="newNodeLabel" placeholder="Input node label">
      <!-- <button @click="addNode">ADD</button> -->
      <span class="node-add" @click="startSim">
        <div class="node-add queue">
          <div class="button-icon">
          <lord-icon
              src="https://cdn.lordicon.com/aklfruoc.json"
              trigger="hover"
              stroke="bold"
              colors="primary:#ffffff,secondary:#e8e230"
              style="width:40px;height:40px">
          </lord-icon>
        </div>
        <div>
            Start Sim
        </div>
        </div>
      </span>
      <input type="text" v-model="prodNum" placeholder="Input number of products">
      <span v-if="this.stopSim" class="node-add" @click="restartSim">
        <div class="node-add queue">
          <div class="button-icon">
          <lord-icon
            src="https://cdn.lordicon.com/rsbokaso.json"
            trigger="hover"
            stroke="bold"
            colors="primary:#ffffff"
            style="width:40px;height:40px">
          </lord-icon>
        </div>
        <div>
            Restart Sim
        </div>
        </div>
      </span>
    </div>
    
    <simple-flowchart :scene.sync="scene"
      ref="flowChart" 
      @nodeClick="nodeClick"
      @nodeDelete="nodeDelete"
      @linkBreak="linkBreak"
      @linkAdded="linkAdded"
      @canvasClick="canvasClick"
      :height="800"/>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import SimpleFlowchart from './components/SimpleFlowchart.vue'


export default {
  name: 'app',
  components: {
    SimpleFlowchart
  },
  setup() {
    const flowChart = ref(null);
    return {
      flowChart
    }
  },
  data() {
    return {
      scene: {
        centerX: 1024,
        centerY: 140,
        scale: 1,
        nodes: [
        ],
        links: [
        ]
      },
      newNodeType: 0,
      newNodeLabel: '',
      nodeCategory:[
        'M',
        'Q'
      ],
      queuesNumber: 0,
      machinesNumber: 0,
      queuesColors: [],
      machinesColors: [],
      stopSim: false,
      prodNum: '',
      // graphMatrix: []
    //   findNodeWithID: function(id){
    //   return this.scene.nodes.find((item) => {
    //       return id === item.id
    //   })
    // }
    }
  },
  methods: {
    canvasClick(e) {
      // console.log('canvas Click, event:', e)
    },
    addNode() {
      let maxID = Math.max(0, ...this.scene.nodes.map((link) => {
        return link.id
      }))
      this.scene.nodes.push({
        id: maxID + 1,
        x: -400,
        y: 50,
        // color: null,
        type: this.nodeCategory[this.newNodeType],
        label: this.newNodeLabel ? this.newNodeLabel: `Node${maxID + 1}`,
        num: this.newNodeType==0 ? this.machinesNumber: this.queuesNumber,
      })
      this.newNodeLabel = ''
      console.log(this.scene.nodes)
    },

    nodeClick(id) {
      // console.log('node click', id);
    },
    nodeDelete(ID) {
      // this.scene.nodes.forEach(n=> {
      //   if(n.id == ID){
      //     if (n.type === 'Q'){
      //       this.queuesNumber = this.queuesNumber - 1
      //     }else{
      //       this.machinesNumber = this.machinesNumber - 1
      //     }
      //   }
      // })
      // console.log('node delete', id);
    },
    linkBreak(id) {
      // console.log('link break', id);
    },
    linkAdded(link) {
      // console.log('new link added:', link);
    },
    updateColors(){
      console.log(this.stopSim);
      fetch('http://localhost:8080/getQueues', {
        method: "GET",
        // headers: {
        //   "Content-type": "application/json; charset=UTF-8",
        // }
      })
        .then(res => res.json())
        .then(data => {
          console.log(data)
          this.queuesColors = data
          for(let ID in this.queuesColors){
            this.scene.nodes.forEach(n=> {
              let q
              if (n.id-1 == ID){
                if (this.queuesColors[ID] != null){
                  q = Array.from(this.queuesColors[ID]).length
                }else{
                  q = 0;
                }
                n.count = q
                console.log(n)
              }
            })
          }
        })
      
      fetch('http://localhost:8080/getMachines', {
        method: "GET",
      })
        .then(res => res.json())
        .then(data => {
          // console.log(data)
          this.machinesColors = data
          console.log(this.machinesColors)
          console.log(this.scene.nodes)
          for(let ID in this.machinesColors){
            this.scene.nodes.forEach(n=> {
              let color
              if (n.id-1 == ID){
                if (this.machinesColors[ID] != null){
                  color = Number(this.machinesColors[ID])
                  if(color < 10){
                    color += 10
                  }
                  console.log((color*0.01))
                  color = (color*0.01)
                  color = Math.floor(color*16777215).toString(16);
                }else{
                  color = 'e0e0e0';
                }
                n.color = '#' + color
                console.log(n)
              }
                // console.log(Math.floor(color*16777215))
            })
            // console.log(node)
            // node.color = '#' + Math.floor((id/100)*16777215).toString(16);
          }
        })

      

    },
    startSim(){
      this.stopSim = false
      this.flowChart.initializeMatrix()
      let mat = this.flowChart.updateMatrix();
      console.log(mat);

      fetch('http://localhost:8080/graph', {
        method: "POST",
        body: JSON.stringify(mat),
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        }
      })
        .then(res => res.text())
        .then(data => console.log(data)) 
        
        this.runSim();
        var autoReloadInterval = setInterval(()=>{
          this.updateColors();
          if(this.stopSim){
          clearInterval(autoReloadInterval);
          }
        }, 250);
    },
    restartSim(){
      this.stopSim = false
      this.reRunSim();
      var autoReloadInterval = setInterval(()=>{
          this.updateColors();
          if(this.stopSim){
          clearInterval(autoReloadInterval);
          }
      }, 250);
    },    
    async runSim(){
      const url = "http://localhost:8080/simulate?"
      const params = {
        productCount: Number(this.prodNum)
      }
      const query = new URLSearchParams(params)
      const method = "GET"
      fetch(url + query, {
        method: method,
      })
          .then(res => res.text())
          .then((data) => {
            this.stopSim = true
            console.log(data)
          })
    },
    async reRunSim(){
      const url = "http://localhost:8080/restart"
      const method = "GET"
      fetch(url, {
        method: method,
      })
          .then(res => res.text())
          .then((data) => {
            this.stopSim = true
            console.log(data)
          })
    }
  }
}
</script>

<style scoped lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  // background-color: #00A48A;
  // backdrop-filter: blur(10px);
  margin: 0;
  overflow: hidden;
  height: 800px;
  background-color: #DFF8D5;
  .tool-wrapper {
    position: relative;
    display: flex;
    justify-content: center;
    background: rgba(0, 164, 138, 0.66);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(7.6px);
    -webkit-backdrop-filter: blur(7.6px);
  }
}
.node-add{
      margin: auto;
      /* width: 100%; */
      display: flex;
      flex-direction: column;
      align-content: center;
      color: #fff;;
      border-radius:10px;
      padding: 5px;
      text-align:center;
      transition: 1s;
      background: rgba(0, 164, 138, 0.66);
border-radius: 16px;
box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
backdrop-filter: blur(7.6px);
-webkit-backdrop-filter: blur(7.6px);
    }

.node-add .queue:hover{
  cursor: pointer;
  background-color: #07d800;
  transition: 1s;
}
// .node-add .machine{
//   background-color: #07d800;
// }
.node-add .machine:hover{
  cursor: pointer;
  background-color: rgb(208, 255, 0); //#07d800;
  transition: 1s;
}
.button-icon{
  margin: auto;
}

</style>
