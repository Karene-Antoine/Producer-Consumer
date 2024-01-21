<template>
  <div id="app">
    <h1>Producer/Consumer Simulation</h1>
    <h2>Rabena yostor</h2>
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
      // graphMatrix: []
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
    nodeDelete(id) {
      // console.log('node delete', id);
    },
    linkBreak(id) {
      // console.log('link break', id);
    },
    linkAdded(link) {
      // console.log('new link added:', link);
    },
    startSim(){
      this.flowChart.initializeMatrix()
      let mat = this.flowChart.updateMatrix();
      console.log(mat);
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
